/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 *
 * @author mlvil
 */
public class FileUtilsTest {

    public static final String DEFAULT_DATA_DIRECTORY = "data";
    private final String TEST_DIR_PATH = "src" + File.separator + "test" + File.separator + "java" + File.separator + "temp_dir";
    private Path testDir;

    public FileUtilsTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws IOException {
      testDir = Paths.get(TEST_DIR_PATH); // Specify your path
      Files.createDirectories(testDir); // Ensure the directory exists
    }
    
    @AfterEach
    public void tearDown() throws IOException {
      Files.walk(testDir)
              .map(Path::toFile)
              .forEach(File::delete); // Clean up the directory
    }
    /**
     * Test of constructor, of class FileUtils.
     */
 @Test
    void testConstructorWithUnexistingDirectoryName() {
        String customDir = "my_test_dir";
        String expectedPath = System.getProperty("user.dir") + File.separator + customDir; 
        assertFalse(Files.exists(Path.of(expectedPath))); // directory doesn't exist
        FileUtils customFileUtils = new FileUtils(customDir);
        assertEquals(expectedPath, customFileUtils.getDataDirectory());
        assertTrue(Files.exists(Path.of(expectedPath))); // Check if directory is created
        //Clean up the directory created
        File customDirFile = new File(expectedPath);
        customDirFile.delete();
    }

    @Test
    void testConstructorWithExistingDirectoryName() {
        String customDir = "existing_test_dir";
        String expectedPath = System.getProperty("user.dir") + File.separator + customDir;
        File existingDir = new File(expectedPath);
        existingDir.mkdirs(); // Ensure directory exists
        assertTrue(Files.exists(Path.of(expectedPath))); // Directory should already exist
        FileUtils customFileUtils = new FileUtils(customDir);
        assertEquals(expectedPath, customFileUtils.getDataDirectory());
        assertTrue(Files.exists(Path.of(expectedPath))); // Ensure directory still exists
        // Clean up
        existingDir.delete();
    }
    
    /**
     * Test of createFile, of class FileUtils.
     */
    @Test
    void testCreateFile_Success() throws IOException {
        String filename = "test_file.txt";
        FileUtils fu = new FileUtils(TEST_DIR_PATH);

        assertTrue(fu.createFile(filename));
        assertTrue(Files.exists(testDir.resolve(filename)));
        assertTrue(new File(testDir.resolve(filename).toString()).length()==0); //Check if the file is empty
    }
    @Test
    void testCreateFile_AlreadyExists_ReturnsFalse() throws IOException {
        String filename = "existing_file.txt";
        FileUtils fu = new FileUtils(TEST_DIR_PATH);

        Files.createFile(testDir.resolve(filename)); // Create the file beforehand
        assertFalse(fu.createFile(filename));
        assertTrue(Files.exists(testDir.resolve(filename)));
    }
    @Test
    void testCreateFile_NullParameter_ReturnsFalse() throws IOException {
      String filename = null;
      FileUtils fu = new FileUtils(TEST_DIR_PATH);
      assertFalse(fu.createFile(filename));
    }
    @Test
    void testCreateFile_EmptyParameter_ReturnsFalse() throws IOException {
      String filename = "";
      FileUtils fu = new FileUtils(TEST_DIR_PATH);
      assertFalse(fu.createFile(filename));
    }

    /**
     * Test of deleteFile, of class FileUtils.
     */
    @Test
    void testDeleteFile_Success() throws IOException {
        String filename = "to_delete.txt";
        FileUtils fu = new FileUtils(TEST_DIR_PATH);

        Files.createFile(testDir.resolve(filename));
        assertTrue(fu.deleteFile(filename));
        assertFalse(Files.exists(testDir.resolve(filename)));
    }
    @Test
    void testDeleteFile_NotFound_ReturnsFalse() {
        String filename = "non_existent.txt";
        FileUtils fu = new FileUtils(TEST_DIR_PATH);

        assertFalse(fu.deleteFile(filename));
    }
    @Test
    void testDeleteFile_NullParameter_ReturnsFalse() {
        String filename = null;
        FileUtils fu = new FileUtils(TEST_DIR_PATH);

        assertFalse(fu.deleteFile(filename));
    }
    @Test
    void testDeleteFile_EmptyParameter_ReturnsFalse() {
        String filename = "";
        FileUtils fu = new FileUtils(TEST_DIR_PATH);

        assertFalse(fu.deleteFile(filename));
    }

    /*
     * Test of fileExists, of class FileUtils.
     */
    @Test
    void testFileExists_ExistsAndNotEmpty_ReturnsTrue() throws IOException{
        String filename = "existing_and_not_empty.txt";
        FileUtils fu = new FileUtils(TEST_DIR_PATH);

        Files.writeString(testDir.resolve(filename), "some content");
        assertTrue(fu.fileExists(filename));
    }
    @Test
    void testFileExists_ExistsAndEmpty_ReturnsTrue() throws IOException{
        String filename = "existing_and_empty.txt";
        FileUtils fu = new FileUtils(TEST_DIR_PATH);

        Files.createFile(testDir.resolve(filename));
        assertTrue(fu.fileExists(filename));
    }
    @Test
    void testFileExists_NotExists_ReturnsFalse() {
        String filename = "not_existing.txt";
        FileUtils fu = new FileUtils(TEST_DIR_PATH);

        assertFalse(fu.fileExists(filename));
    }    
    @Test
    void testFileExists_EmptyFilename_ReturnsFalse() {
        String filename = "";
        FileUtils fu = new FileUtils(TEST_DIR_PATH);

        assertFalse(fu.fileExists(filename));
    }
    @Test
    void testFileExists_NullFilename_ReturnsFalse() {
        String filename = null;
        FileUtils fu = new FileUtils(TEST_DIR_PATH);

        assertFalse(fu.fileExists(filename));
    }

    /**
     * Test of insertMarketInfoToFile, of class FileUtils.
     */
    @Test
    public void testInsertMarketInfoIntoFile_HappyPath() throws IOException {
      String supermarketName = "Supermarket A";
      String supermarketCity = "City X";
      float supermarketLongitude = 10.5f;
      float supermarketLatitude = 52.3f;
      String fileName = "test_file.txt";
      FileUtils fu = new FileUtils(TEST_DIR_PATH);

      assertTrue(fu.insertMarketInfoIntoFile(fileName, supermarketName, supermarketCity, supermarketLongitude, supermarketLatitude));
      String filePath = fu.getFilePath(fileName);
      File tempFile = new File(filePath);

      String line;
        try ( // Read the content of the file
                BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            line = reader.readLine();
        }

      // Assert that the written line matches the expected format
      String expectedLine = LocalDateTime.now().format(DateTimeFormatter.ofPattern(Constants.DATE_FORMAT)) + "," + supermarketName + "," +
          supermarketCity + "," + supermarketLongitude + "," + supermarketLatitude;
      assertEquals(expectedLine, line);

      // Delete the temporary file
      tempFile.delete();
    }

    @Test
    public void testInsertMarketInfoIntoFile_NullFileName_NotException_ReturnsFalse() throws IOException {
      String fileName = null;
      String supermarketName = "Supermarket A";
      String supermarketCity = "City X";
      float supermarketLongitude = 10.5f;
      float supermarketLatitude = 52.3f;
      FileUtils fu = new FileUtils(TEST_DIR_PATH);

      
      // Expect no exception when other parameters are valid
      assertFalse(fu.insertMarketInfoIntoFile(fileName, supermarketName, supermarketCity, supermarketLongitude, supermarketLatitude));
    }
    @Test
    public void testInsertMarketInfoIntoFile_NullSupermarketName_NotException_ReturnsFalse() {
      String fileName = "temp_file.txt";
      String supermarketName = null;
      String supermarketCity = "City X";
      float supermarketLongitude = 10.5f;
      float supermarketLatitude = 52.3f;
      FileUtils fu = new FileUtils(TEST_DIR_PATH);

      // Expect no exception when other parameters are valid
      assertFalse(fu.insertMarketInfoIntoFile(fileName, supermarketName, supermarketCity, supermarketLongitude, supermarketLatitude));
    }
    @Test
    public void testInsertMarketInfoIntoFile_NullSupermarketCityName_NotException_ReturnsFalse() {
      String fileName = "temp_file.txt";
      String supermarketName = "Supermarket A";
      String supermarketCity = null;
      float supermarketLongitude = 10.5f;
      float supermarketLatitude = 52.3f;
      FileUtils fu = new FileUtils(TEST_DIR_PATH);

      // Expect no exception when other parameters are valid
      assertFalse(fu.insertMarketInfoIntoFile(fileName, supermarketName, supermarketCity, supermarketLongitude, supermarketLatitude));
    }
    @Test
    public void testInsertMarketInfoIntoFile_EmptyFileName_NotException_ReturnsFalse() throws IOException {
      String fileName = "";
      String supermarketName = "Supermarket A";
      String supermarketCity = "City X";
      float supermarketLongitude = 10.5f;
      float supermarketLatitude = 52.3f;
      FileUtils fu = new FileUtils(TEST_DIR_PATH);

      
      // Expect no exception when other parameters are valid
      assertFalse(fu.insertMarketInfoIntoFile(fileName, supermarketName, supermarketCity, supermarketLongitude, supermarketLatitude));
    }
    @Test
    public void testInsertMarketInfoIntoFile_EmptySupermarketName_NotException_ReturnsFalse() {
      String fileName = "temp_file.txt";
      String supermarketName = null;
      String supermarketCity = "City X";
      float supermarketLongitude = 10.5f;
      float supermarketLatitude = 52.3f;
      FileUtils fu = new FileUtils(TEST_DIR_PATH);

      // Expect no exception when other parameters are valid
      assertFalse(fu.insertMarketInfoIntoFile(fileName, supermarketName, supermarketCity, supermarketLongitude, supermarketLatitude));
    }
    @Test
    public void testInsertMarketInfoIntoFile_EmptySupermarketCityName_NotException_ReturnsFalse() {
      String fileName = "temp_file.txt";
      String supermarketName = "Supermarket A";
      String supermarketCity = null;
      float supermarketLongitude = 10.5f;
      float supermarketLatitude = 52.3f;
      FileUtils fu = new FileUtils(TEST_DIR_PATH);

      // Expect no exception when other parameters are valid
      assertFalse(fu.insertMarketInfoIntoFile(fileName, supermarketName, supermarketCity, supermarketLongitude, supermarketLatitude));
    }

  /**
  * Test of extractSupermarketList, of class FileUtils.
  */
  @Test
  public void testExtractSupermarketList_NullFileName() throws IOException {
    FileUtils fu = new FileUtils(TEST_DIR_PATH);
    assertNull(fu.extractSupermarketList(null));
    }

  @Test
  public void testExtractSupermarketList_EmptyFileName() throws IOException {
    FileUtils fu = new FileUtils(TEST_DIR_PATH);
    assertNull(fu.extractSupermarketList(""));
    }

  @Test
  public void testExtractSupermarketList_FileNotExists() throws IOException {
    String filename = "non_existent_file.txt";
    FileUtils fu = new FileUtils(TEST_DIR_PATH);
    assertNull(fu.extractSupermarketList(filename));
    }

  @Test
  public void testExtractSupermarketList_EmptyFile() throws IOException {
    String filename = "empty_file.txt";
    FileUtils fu = new FileUtils(TEST_DIR_PATH);
    fu.createFile(filename);
    String content = fu.extractSupermarketList(filename);
    assertEquals("", content); // Should return an empty string, not null
    }

  @Test
  public void testExtractSupermarketList_WithContent() throws IOException {
      String filename = "with_content.txt";
      String expectedContent = "Line 1\nLine 2\nLine 3\n";
      FileUtils fu = new FileUtils(TEST_DIR_PATH);
      fu.createFile(filename);
      Path filePath = Path.of(fu.getFilePath(filename));
      Files.writeString(filePath, expectedContent);

      String actualContent = fu.extractSupermarketList(filename);
      assertEquals(expectedContent, actualContent);
    }
  }