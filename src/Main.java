import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {

    public static final List<String> Exclusions = Arrays.asList("Main.java");
    static Comparator<File> datecmp = new Comparator<File>() {
        @Override
        public int compare(File o1, File o2) {
            return -Long.compare(o1.lastModified(), o2.lastModified());
        }
    };

    static Comparator<File> namecmp = new Comparator<File>() {
        @Override
        public int compare(File o1, File o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };
    static InputStream consoleIn;
    public static void main(String[] args) {
        consoleIn = System.in;
        File folder = new File("src");
        ArrayList<File> listOfFiles = new ArrayList<>(Arrays.asList(folder.listFiles()));
        Collections.sort(listOfFiles, datecmp);

        int max = listOfFiles.size();
        for (int i = 0; i < max; i++) {
            File f = listOfFiles.get(i);
            if (!f.isFile() || !f.getName().endsWith(".java") || Exclusions.contains(f.getName())) {
                listOfFiles.remove(f);
                i--;
                max--;
            }
        }


        Scanner sc = new Scanner(consoleIn);
        System.out.println("Enter Number or options:");
        int index = 0;
        while (true) {
            System.out.println("N = Next; B = Back; A= AlphaSort; D = DateSort; S = Search; 0 or E = Exit");
            for (int i = index; (i < index + 5 && i < listOfFiles.size()); i++) {
                System.out.println(i + 1 + ": " + listOfFiles.get(i).getName());
            }
            String input = sc.next();
            Integer i = TryParse(input);
            if (i != null && i <= listOfFiles.size()) {
                if (i<=0) {
                    System.out.println("Goobye");
                    return;
                }
                Execute(listOfFiles.get(i - 1));
            } else switch (input.toLowerCase()) {
                case "e":
                    System.out.println("Goobye");
                    return;
                case "n":
                    index += 5;
                    if (index > listOfFiles.size()) index = 0;
                    break;
                case "b":
                    index -= 5;
                    if (index < 0) index = listOfFiles.size()-listOfFiles.size()%5;
                    break;
                case "a":
                    Collections.sort(listOfFiles, namecmp);
                    index = 0;
                    break;
                case "d":
                    Collections.sort(listOfFiles, datecmp);
                    index = 0;
                    break;
                case "s":
                    System.out.println("Input Search Term");
                    String search = sc.next();
                    int results = Search(listOfFiles, search);
                    System.out.println("Found " + results + " results.");
                    index = 0;
                    break;
                default:
                    System.out.println("Incorrect input. Plz try again. Plz.");
                    break;
            }
        }
    }

    private static void Execute(File file) {

        try {

            String problemName = file.getName().replace(".java", "");
            Class<?> clazz = Class.forName(problemName);
            Method m = clazz.getMethod("main", String[].class);
            String[] params = null; // init params accordingly
            ProblemName pn = clazz.getAnnotation(ProblemName.class);
            if (pn != null)
            {
                problemName = pn.name();
            }
            problemName = problemName.toLowerCase();

            if (!SetupInput(problemName)){
                System.out.println("There was no input available online, please place the input with the correct name in the appropriate folder");
                return;
            }
            String inputPath = "input/" + problemName + "/";
            int i = 0;

            try {
                while (true) {
                    i++;
                    File input = new File(inputPath + i + ".in");
                    if (!input.exists()) {
                        if (i == 1) System.out.println("Error. How did we get here?");
                        break;
                    }
                    System.setIn(new FileInputStream(input));
                    System.out.println("----------------Program Output #" + i + "----------------");
                    m.invoke(null, (Object) params);
                    System.out.println();
                    File answer = new File(inputPath + i + ".ans");
                    if (answer.exists()) {
                        System.out.println("----------------Expected Output#"+i+"----------------");
                        BufferedReader br = new BufferedReader(new FileReader(answer));
                        for (String line; (line = br.readLine()) != null; ) {
                            System.out.println(line);
                        }
                        br.close();
                        System.out.println();
                    }
                }
            } finally {
                System.setIn(consoleIn);
            }





            //System.setIn(new FileInputStream("input/" + c.getSimpleName()));

        } catch (ClassNotFoundException | NoSuchMethodException|InvocationTargetException | IllegalAccessException | IOException e) {
            System.out.println("Error During Reflection : " + e.getMessage());

        }


    }

    private static boolean SetupInput(String problemName){

        File folder = new File("input/" + problemName + "/");
        if (folder.exists() && new File("input/" + problemName + "/1.in").exists()) {
            return  true;
        }
        else{
            try {
                IgnoreSSL();
                Document doc = Jsoup.connect("https://open.kattis.com/problems/" + problemName)
                        .userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.101 Safari/537.36")
                        .timeout(10000).get();

                Elements elements = doc.getElementsByClass("problem-download");
                if (elements != null && elements.size() > 0) {
                    Elements e2 = elements.get(0).getElementsByTag("a");
                    if (e2 != null && e2.size() > 0 && e2.hasAttr("href")) {
                        String dlink = "https://open.kattis.com" + e2.get(0).attr("href");
                        URL website = new URL(dlink);
                        File dest = new File("temp/");
                        dest.mkdirs();
                        System.out.println(dest.getCanonicalPath());
                        //noinspection ResultOfMethodCallIgnored
                        Download("temp/file.zip", new URL(dlink));
                        folder.mkdirs();


                        try {
                            UnZip("temp/file.zip", folder.getPath());
                            ArrayList<File> files = new ArrayList<File>(Arrays.asList(folder.listFiles()));
                            Collections.sort(files, namecmp);
                            int ansCount = 1, inCount = 1;
                            for(File oldFile : files)
                            {
                                if (oldFile.getName().endsWith("ans"))
                                    Files.move(oldFile.toPath(), oldFile.toPath().resolveSibling("" + ansCount++ + ".ans"));
                                else if (oldFile.getName().endsWith("in"))
                                    Files.move(oldFile.toPath(), oldFile.toPath().resolveSibling("" + inCount++ + ".in"));
                            }


                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        return true;
                    }
                }else{
                    Elements sampleElements = doc.getElementsByClass("sample");
                    if(sampleElements.size() <=0) return false;
                    int i = 0;
                    folder.mkdirs();
                    for (Element sample: sampleElements){
                        i++;
                        Elements data = sample.getElementsByTag("pre");
                        if(data.size()<=0)return false;

                        PrintWriter out = new PrintWriter("input/" + problemName + "/"+i+".in");
                        out.print(data.get(0).text());
                        out.close();
                        out = new PrintWriter("input/" + problemName + "/"+ i+".ans");
                        out.print(data.get(1).text());
                        out.close();
                    }
                    return true;
                }
            } catch (IOException e) {
                System.out.println("Error During File download. Try again later : "+  e.getMessage());
                return false;
            }
        }
        return false;
    }

    private static void IgnoreSSL() {

        System.setProperty("jsse.enableSNIExtension", "false");
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }};

// Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            ;
        }
    }


    static int Search(ArrayList<File> list, String id) {
        int count = 0;

        ArrayList<File> ret = new ArrayList<>(list);
        list.clear();

        for (File f : ret) {
            if (f.getName().toLowerCase().contains(id.toLowerCase())){
                list.add(f);
                count++;
            }
        }
        for (File f : ret) {
            if (!f.getName().toLowerCase().contains(id.toLowerCase())) list.add(f);
        }

        return count;
    }

    public static void Download(String fileName, URL link) throws IOException {
        //Code to download
        InputStream in = new BufferedInputStream(link.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n = 0;
        while (-1 != (n = in.read(buf))) {
            out.write(buf, 0, n);
        }
        out.close();
        in.close();
        byte[] response = out.toByteArray();

        FileOutputStream fos = new FileOutputStream(fileName);
        fos.write(response);
        fos.close();
        //End download code

        System.out.println("Finished");

    }

    public static void UnZip(String zipFile, String outputFolder) throws IOException {

        System.out.println("Begin unzip " + zipFile + " into " + outputFolder);
        ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
        ZipEntry ze = zis.getNextEntry();
        int ansCount = 0, inCount = 0;
        while (ze != null) {
            String entryName = ze.getName();
            System.out.print("Extracting " + entryName + " -&gt; " + outputFolder + File.separator + entryName + "...");

            //zackplugin.php --rip in pieces R.I.P.
            //String[] split = entryName.split("\\.");
            //String extension = split[split.length-1];
            //String middle = split[split.length-2];
            //if (extension.equals("ans") || extension.equals("in"))
            //{
            //    String newfilename = "";
            //    for(int i = 0; i < middle.length(); i++)
            //    {
            //        Character c = middle.charAt(i);
            //        if (Character.isDigit(c)) newfilename += c;
            //    }
            //    if (newfilename.equals(""))
            //    {
            //        if (extension.equals("ans")) newfilename = "" + ++ansCount;
            //        else if (extension.equals("in")) newfilename = "" + ++inCount;
            //    }
            //    newfilename += "." + extension;
            //    entryName = newfilename;
            //}
            File f = new File(outputFolder + File.separator + entryName);
            //create all folder needed to store in correct relative path.
            f.getParentFile().mkdirs();
            FileOutputStream fos = new FileOutputStream(f);
            int len;
            byte buffer[] = new byte[1024];
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            System.out.println("OK!");
            ze = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();

        System.out.println(zipFile + " unzipped successfully");
    }

    //Ugh. I hate this language.
    public static Integer TryParse(String obj) {
        Integer retVal;
        try {
            retVal = Integer.parseInt(obj);
        } catch (NumberFormatException nfe) {
            retVal = null; // or null if that is your preference
        }
        return retVal;
    }
}
@Retention(RetentionPolicy.RUNTIME)
@interface ProblemName
{
    String name();
}