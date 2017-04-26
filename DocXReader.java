public class DocXReader {

  private String file;
  private ReadZip rz;

  public static void main(String[] args) {

  }

  public DocXReader(String filename) {
    file = filename;
  }

  public String readFile() { //reads the Docx file and returns the String contents of the desired subfile
    String docText = "";

    try{
        // A ZipInputStream reads in the zip file
        ZipInputStream zis = new ZipInputStream(new FileInputStream("document.xml"),
                                                Charset.forName("UTF-8"));

        // Iterate through the files up the zipped folder
        ZipEntry ze = zis.getNextEntry();

        while(ze != null) {
          if (ze.getName().substring(ze.getName().indexOf('/')).equals("document.xml")) {
            System.out.println("Opening: " + ze.getName());
            docText += readInFileContents(zis);
            break;
          }
          else {
            ze = zis.getNextEntry();
          }
        }

        // Close the stream to release the file handle
        zis.closeEntry();
        zis.close();
    }catch(IOException ex){
        // We had some kind of problem, so print its stack trace
        ex.printStackTrace();
    }
    return docText;
  }



}
