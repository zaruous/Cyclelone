package def;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// Placeholder for custom utility classes.
// You will need to replace these with your actual implementations.

/**
 * Placeholder for database utility functions.
 * In a real application, this would use proper JDBC connection pooling and error handling.
 */
class DbUtil {
    public static Connection getConnectionByAliasName(String aliasName) throws SQLException {
        System.out.println("Mock: Getting connection for alias: " + aliasName);
        return DriverManager.getConnection("jdbc:sqlite::memory:"); // Example for SQLite in-memory
    }

    public static List<Map<String, Object>> select(Connection con, String sql) throws SQLException {
        List<Map<String, Object>> resultList = new ArrayList<>();
        // This is a mock implementation. Replace with actual database query logic.
        System.out.println("Mock: Executing SQL: " + sql);

        // Example mock data for demonstration
        if (sql.contains("MADMFUNDEF")) {
            Map<String, Object> row1 = new HashMap<>();
            row1.put("FUNC_LINK", "path/to/my/function1");
            row1.put("FUNC_DESC", "Function Description 1");
            row1.put("MTL_VALUE", "계산식1"); // Example translated value
            row1.put("FUNC_CODE", "FNC001");
            resultList.add(row1);

            Map<String, Object> row2 = new HashMap<>();
            row2.put("FUNC_LINK", "path/to/another/function2");
            row2.put("FUNC_DESC", "Function Description 2");
            row2.put("MTL_VALUE", null); // No translation
            row2.put("FUNC_CODE", "FNC002");
            resultList.add(row2);
        }
      
        
        return resultList;
    }
}

/**
 * Placeholder for file utility functions.
 */
class FileUtil {
    public static String readConversion(File file) throws IOException {
//        System.out.println("Mock: Reading file: " + file.getAbsolutePath());
//        // This is a mock implementation. Replace with actual file reading logic.
//        // Example XML content for testing:
//        if (file.getName().contains("function1.clx")) {
//            return "<root xmlns:cl=\"http://example.com/cl\">\n" +
//                   "  <cl:appspec preferred-width=\"1540\" title=\"원부자재 투입량 등록\"/>\n" +
//                   "  <cl:submission id=\"submission1\" method=\"post\">\n" +
//                   "    <cl:expbind expression=\"ServiceUrls.ADM_SERVICE_URL + 'some/api/path'\"/>\n" +
//                   "  </cl:submission>\n" +
//                   "  <cl:submission id=\"submission2\" method=\"get\">\n" +
//                   "    <cl:expbind expression=\"ServiceUrls.INV_SERVICE_URL + 'another/api?param=value'\"/>\n" +
//                   "  </cl:submission>\n" +
//                   "</root>";
//        } else if (file.getName().contains("function2.clx")) {
//             return "<root xmlns:cl=\"http://example.com/cl\">\n" +
//                   "  <cl:appspec preferred-width=\"1000\" title=\"재고 조회\"/>\n" +
//                   "  <cl:submission id=\"submission3\" method=\"post\">\n" +
//                   "    <cl:expbind expression=\"ServiceUrls.WIP_SERVICE_URL + 'wip/data'\"/>\n" +
//                   "  </cl:submission>\n" +
//                   "</root>";
//        }
        return Files.readString(file.toPath());
    }

    public static void writeFile(File file, String content) throws IOException {
        System.out.println("Mock: Writing content to file: " + file.getAbsolutePath());
        Files.write(Paths.get(file.getAbsolutePath()), content.getBytes("utf-8"));
    }
}

/**
 * Placeholder for XML utility functions.
 */
class XMLUtils2 {
    public static Document load(String xmlContent) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true); // Important for XPath with namespaces
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new java.io.ByteArrayInputStream(xmlContent.getBytes()));
    }
}

/**
 * Data Transfer Object for Submission Model.
 */
class SubmissionModelDVO {
    private String id; // Not used in Groovy code directly, but kept for completeness
    private File f;
    private String title;
    private List<Submission> submissions;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public File getF() {
        return f;
    }

    public void setF(File f) {
        this.f = f;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(f.getName()).append("\n");
        sb.append("\t").append(title).append("\n");
        for (Submission s : submissions) {
            sb.append("\t\t").append(s.getUrl()).append("\n");
        }
        return sb.toString();
    }
}

/**
 * Data Transfer Object for a single Submission.
 */
class Submission {
    private String id;
    private String url;
    private String comment; // Not used in Groovy code directly, but kept for completeness
    private String method;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}

class GroovyToJavaConverter {

    private static final Map<String, String> urlMapping = new HashMap<>();

    static {
        urlMapping.put("ServiceUrls.ADM_SERVICE_URL", "'/v1/adm/'");
        urlMapping.put("ServiceUrls.INV_SERVICE_URL", "'/v1/inv/'");
        urlMapping.put("ServiceUrls.WIP_SERVICE_URL", "'/v1/wip/'");
        urlMapping.put("ServiceUrls.QCM_SERVICE_URL", "'/v1/qcm/'");
        urlMapping.put("ServiceUrls.RAS_SERVICE_URL", "'/v1/ras/'");
        urlMapping.put("ServiceUrls.SCHEDULING_SERVICE_URL", "'/v1/scheduling/'");
        urlMapping.put("ServiceUrls.RPT_SERVICE_URL", "'/v1/rpt/'");
        urlMapping.put("ServiceUrls.OAUTH_SERVICE_URL", "'/iam/'");
        urlMapping.put("ServiceUrls.RMS_SERVICE_URL", "'/v1/rms/'");
        urlMapping.put("ServiceUrls.BAS_SERVICE_URL", "'/v1/bas/'");
        urlMapping.put("ServiceUrls.SPC_SERVICE_URL", "'/v1/spc/'");
        urlMapping.put("ServiceUrls.EDC_SERVICE_URL", "'/v1/edc/'");
        urlMapping.put("ServiceUrls.EIS_SERVICE_URL", "'/v1/eis/'");
        urlMapping.put("ServiceUrls.BASE_SERVICE_URL", "");
        urlMapping.put("ServiceUrls.LIS_SERVICE_URL", "'/v1/lis/'");
        urlMapping.put("ServiceUrls.TST_SERVICE_URL", "'/v1/tst/'");
        urlMapping.put("ServiceUrls.CSUM_SERVICE_URL", "'/v1/csum/'");
        urlMapping.put("ServiceUrls.CACT_SERVICE_URL", "'/v1/cact/'");
        urlMapping.put("ServiceUrls.CINV_SERVICE_URL", "'/v1/cinv/'");
        urlMapping.put("ServiceUrls.CWIP_SERVICE_URL", "'/v1/cwip/'");
        urlMapping.put("ServiceUrls.CQCM_SERVICE_URL", "'/v1/cqcm/'");
        urlMapping.put("ServiceUrls.CMMS_SERVICE_URL", "'/v1/cmms/'");
        urlMapping.put("ServiceUrls.IINF_SERVICE_URL", "'/v1/iinf/'");
        urlMapping.put("ServiceUrls.CRTD_SERVICE_URL", "'/v1/crtd/'");
        urlMapping.put("ServiceUrls.CFMS_SERVICE_URL", "'/v1/cfms/'");
        urlMapping.put("ServiceUrls.CSAP_SERVICE_URL", "'/v1/csap/'");
    }

    public static void main(String[] args) {
        File outFileName = new File("서브미션 및 기능설정_주소_리스트.txt");
        String menuName = "계산식";
        String rootPath = "C:/NexplantMESplus/workspace_newpower/client_akc/mesplus-ui/src/";

        String sql = String.format(
                " select ISNULL(lang.MTL_VALUE, func.FUNC_DESC) as MTL_VALUE , * from MADMFUNDEF func left join MADMMTLVAL lang\n" +
                " \ton func.FUNC_DESC  = lang.MTL_CODE \n" +
                " \tand lang.LANG_CODE  = 'ko'\n" +
                " \tand lang.MTL_VALUE is not null\n" +
                " \t/* and lang.MTL_VALUE like '%%%s%%' */", menuName);

        System.out.println(sql);

        Connection con = null;
        List<Map<String, Object>> matinfoList = new ArrayList<>();
        try {
            con = DbUtil.getConnectionByAliasName("akc");
            matinfoList = DbUtil.select(con, sql);
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
            return; // Exit if database connection/query fails
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println("Error closing connection: " + e.getMessage());
                }
            }
        }

        StringBuilder buffer = new StringBuilder();
        for (Map<String, Object> m : matinfoList) {
            String link = (String) m.get("FUNC_LINK");
            String funcMenuName = (String) m.get("MTL_VALUE");
            if (funcMenuName == null) { // Fallback if MTL_VALUE is null, use FUNC_DESC
                funcMenuName = (String) m.get("FUNC_DESC");
            }
            String funcCode = (String) m.get("FUNC_CODE");

            String clxFileFullPath = rootPath + link + ".clx";

            File f = new File(clxFileFullPath);
            if (!f.exists()) {
                System.out.println("\tfile not exists. [ " + clxFileFullPath + " ] ");
                continue; // Use continue to mimic Groovy's return in each closure
            }

            SubmissionModelDVO dvo;
            try {
                dvo = extract(f);
            } catch (Exception ex) {
                System.err.println("Error extracting from file " + clxFileFullPath + ": " + ex.getMessage());
                ex.printStackTrace();
                continue;
            }

            String title = "[" + funcMenuName + "](" + funcCode + ")";
            buffer.append("\n").append(title).append("\t").append(link + ".clx").append("\t(").append(dvo.getTitle() == null ? "" : dvo.getTitle()).append(")");

            for (Submission sm : dvo.getSubmissions()) {
                String url = sm.getUrl();
                String id = sm.getId();
                String method = sm.getMethod() == null ? "post" : sm.getMethod();

                for (Map.Entry<String, String> entry : urlMapping.entrySet()) {
                    url = url.replace(entry.getKey(), entry.getValue());
                }

                int questionIndex = url.indexOf('?');
                if (questionIndex != -1) {
                    // This logic seems a bit off from the original Groovy, which adds a quote.
                    // Assuming the intent is to truncate at '?' and add a quote if it was there.
                    // The original Groovy: url = url.substring(0, questionIndex) +"\"";
                    // This might lead to invalid URLs. I'll keep the original logic's effect.
                    url = url.substring(0, questionIndex) + "\"";
                }

                String[] urlarr = url.split("\\+");
                try {
                    // Mimicking Groovy's inject (reduce)
                    String processedUrl = "";
                    for (String val : urlarr) {
                        processedUrl += val.trim().replace("'", "").replace("\"", "");
                    }
                    buffer.append("\n").append("\t[").append(method).append("]").append(processedUrl).append("\t[").append(id).append("]");
                } catch (Exception ex) {
                    // This catch block in Groovy was for Eval.me, which is removed.
                    // Keeping it for general URL processing errors, though less likely now.
                    buffer.append("\n").append("\t[").append(method).append("]").append(url);
                    System.err.println("Problem processing URL: " + url + " - " + ex.getMessage());
                }
            }
        }

        String text = buffer.toString();
        System.out.println(text);
        try {
            FileUtil.writeFile(outFileName, text);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Extracts submission information from a CLX XML file.
     *
     * @param f The CLX file to parse.
     * @return A SubmissionModelDVO containing the extracted data.
     * @throws Exception if parsing or file operations fail.
     */
    private static SubmissionModelDVO extract(File f) throws Exception {
        SubmissionModelDVO d = new SubmissionModelDVO();
        d.setF(f);

        String xmlContent = FileUtil.readConversion(f);
        Document doc = XMLUtils2.load(xmlContent);

        // Setup XPath with Namespace Context for 'cl' prefix
        XPath xpath = XPathFactory.newInstance().newXPath();
        // Registering a simple namespace context for 'cl'
        xpath.setNamespaceContext(new javax.xml.namespace.NamespaceContext() {
            @Override
            public String getNamespaceURI(String prefix) {
                if (prefix.equals("cl")) {
                    return "http://example.com/cl"; // Replace with your actual CLX namespace URI
                }
                return null;
            }

            @Override
            public String getPrefix(String namespaceURI) {
                if (namespaceURI.equals("http://example.com/cl")) {
                    return "cl";
                }
                return null;
            }

            @Override
            public java.util.Iterator<String> getPrefixes(String namespaceURI) {
                return null; // Not strictly needed for this use case
            }
        });

        // Extract title
        Node nTitle = (Node) xpath.evaluate("//cl:appspec/@title", doc, XPathConstants.NODE);
        if (nTitle != null) {
            d.setTitle(nTitle.getNodeValue());
        }

        // Extract submissions
        NodeList submissions = (NodeList) xpath.evaluate("//cl:submission", doc, XPathConstants.NODESET);

        List<Submission> list = new ArrayList<>(submissions.getLength());
        d.setSubmissions(list);

        for (int i = 0; i < submissions.getLength(); i++) {
            Node n = submissions.item(i);

            Node exp = (Node) xpath.evaluate("cl:expbind/@expression", n, XPathConstants.NODE);
            if (exp == null) {
                continue; // Mimics Groovy's return in closure
            }

            Node nMethod = (Node) xpath.evaluate("@method", n, XPathConstants.NODE);
            String method = "post";
            if (nMethod != null) {
                method = nMethod.getNodeValue();
            }

            Node nid = (Node) xpath.evaluate("@id", n, XPathConstants.NODE);
            Submission e = new Submission();
            e.setUrl(exp.getNodeValue());
            e.setMethod(method);
            e.setId(nid != null ? nid.getNodeValue() : null); // Handle case where id might be null
            list.add(e);
        }
        return d;
    }
}
