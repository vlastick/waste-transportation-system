package one.vladimir.impl.geo;

import one.vladimir.api.GeoService;
import one.vladimir.api.pojo.Point;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

@Service("geo")
public class GeoServiceImpl implements GeoService {

    // overpass-api.de is blocked by roskomnadzor - check other services or solutions.
    private final String OVERPASS_API = "http://www.overpass-api.de/api/interpreter";
    private final String OPENSTREETMAP_API_06 = "http://www.openstreetmap.org/api/0.6/";

    @PostConstruct
    public void postConstructLog() {

    }

    public static String toString(Document doc) {
        try {
            StringWriter sw = new StringWriter();
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            transformer.transform(new DOMSource(doc), new StreamResult(sw));
            return sw.toString();
        } catch (Exception ex) {
            throw new RuntimeException("Error converting to String", ex);
        }
    }


    public String getResult(String query) {

        String hostname = OVERPASS_API;
        String queryString  = "is_in(61.2246,30.05194);out;";
        String resultString = "";

        try {
            URL osm = new URL(hostname);
            HttpURLConnection connection = (HttpURLConnection) osm.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            DataOutputStream printout = new DataOutputStream(connection.getOutputStream());
            printout.writeBytes("data=" + URLEncoder.encode(queryString, "utf-8"));
            printout.flush();
            printout.close();

            DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
            Document d = docBuilder.parse(connection.getInputStream());

            resultString = toString(d);

        }
        catch (ParserConfigurationException e){
            e.printStackTrace();
            resultString += e.toString();
        }
        catch (SAXException e){
            e.printStackTrace();
            resultString += e.toString();
        }
        catch (MalformedURLException e){
            e.printStackTrace();
            resultString += e.toString();
        }
        catch (java.io.IOException e){
            e.printStackTrace();
            resultString += e.toString();
        }

        if(resultString.equals("")){
            resultString = "There is no answer message with exceptions";
        }

        return resultString;
    }

    @Override
    public String getIslandName(Point point) {

        return null;
    }

    private Boolean isLand(String str) {
        String testStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<osm generator=\"Overpass API 0.7.55.7 8b86ff77\" version=\"0.6\">\n" +
                "    \n" +
                "    <note>The data included in this document is from www.openstreetmap.org. The data is made available under ODbL.</note>\n" +
                "    \n" +
                "    <meta areas=\"2019-05-07T08:50:02Z\" osm_base=\"2019-05-07T09:19:02Z\"/>\n" +
                "      \n" +
                "    <area id=\"2499065722\">\n" +
                "            \n" +
                "        <tag k=\"name\" v=\"остров Пиени-Хейпосаари\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ru\" v=\"остров Пиени-Хейпосаари\"/>\n" +
                "            \n" +
                "        <tag k=\"place\" v=\"islet\"/>\n" +
                "          \n" +
                "    </area>\n" +
                "      \n" +
                "    <area id=\"3600060189\">\n" +
                "            \n" +
                "        <tag k=\"ISO3166-1\" v=\"RU\"/>\n" +
                "            \n" +
                "        <tag k=\"ISO3166-1:alpha2\" v=\"RU\"/>\n" +
                "            \n" +
                "        <tag k=\"ISO3166-1:alpha3\" v=\"RUS\"/>\n" +
                "            \n" +
                "        <tag k=\"ISO3166-1:numeric\" v=\"643\"/>\n" +
                "            \n" +
                "        <tag k=\"admin_level\" v=\"2\"/>\n" +
                "            \n" +
                "        <tag k=\"alt_name:eo\" v=\"Rusio;Ruslando\"/>\n" +
                "            \n" +
                "        <tag k=\"border_type\" v=\"nation\"/>\n" +
                "            \n" +
                "        <tag k=\"boundary\" v=\"administrative\"/>\n" +
                "            \n" +
                "        <tag k=\"currency\" v=\"RUB\"/>\n" +
                "            \n" +
                "        <tag k=\"default_language\" v=\"ru\"/>\n" +
                "            \n" +
                "        <tag k=\"flag\" v=\"http://upload.wikimedia.org/wikipedia/commons/f/f3/Flag_of_Russia.svg\"/>\n" +
                "            \n" +
                "        <tag k=\"int_name\" v=\"Russia\"/>\n" +
                "            \n" +
                "        <tag k=\"int_ref\" v=\"RU\"/>\n" +
                "            \n" +
                "        <tag k=\"name\" v=\"Россия\"/>\n" +
                "            \n" +
                "        <tag k=\"name:UN:ar\" v=\"الاتحاد الروسي\"/>\n" +
                "            \n" +
                "        <tag k=\"name:UN:en\" v=\"Russian Federation (the)\"/>\n" +
                "            \n" +
                "        <tag k=\"name:UN:es\" v=\"Federación de Rusia (la)\"/>\n" +
                "            \n" +
                "        <tag k=\"name:UN:fr\" v=\"Fédération de Russie (la)\"/>\n" +
                "            \n" +
                "        <tag k=\"name:UN:ru\" v=\"Российская Федерация\"/>\n" +
                "            \n" +
                "        <tag k=\"name:UN:zh\" v=\"俄罗斯联邦\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ab\" v=\"Урыстәыла\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ace\" v=\"Rusia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:af\" v=\"Rusland\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ak\" v=\"Russia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:als\" v=\"Russland\"/>\n" +
                "            \n" +
                "        <tag k=\"name:am\" v=\"ሩሲያ\"/>\n" +
                "            \n" +
                "        <tag k=\"name:an\" v=\"Rusia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ang\" v=\"Russland\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ar\" v=\"روسيا\"/>\n" +
                "            \n" +
                "        <tag k=\"name:arc\" v=\"ܪܘܣܝܐ\"/>\n" +
                "            \n" +
                "        <tag k=\"name:arz\" v=\"روسيا\"/>\n" +
                "            \n" +
                "        <tag k=\"name:as\" v=\"ৰুশিযা\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ast\" v=\"Rusia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:av\" v=\"Россиялъул Федерация\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ay\" v=\"Rusiya\"/>\n" +
                "            \n" +
                "        <tag k=\"name:az\" v=\"Rusiya\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ba\" v=\"Рәсәй\"/>\n" +
                "            \n" +
                "        <tag k=\"name:bar\" v=\"Russland\"/>\n" +
                "            \n" +
                "        <tag k=\"name:bat-smg\" v=\"Rosėjė\"/>\n" +
                "            \n" +
                "        <tag k=\"name:bcl\" v=\"Rusya\"/>\n" +
                "            \n" +
                "        <tag k=\"name:be\" v=\"Расійская Федэрацыя\"/>\n" +
                "            \n" +
                "        <tag k=\"name:be-tarask\" v=\"Расея\"/>\n" +
                "            \n" +
                "        <tag k=\"name:bg\" v=\"Русия\"/>\n" +
                "            \n" +
                "        <tag k=\"name:bi\" v=\"Rusia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:bjn\" v=\"Rusia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:bm\" v=\"Risila\"/>\n" +
                "            \n" +
                "        <tag k=\"name:bn\" v=\"রাশিয়া\"/>\n" +
                "            \n" +
                "        <tag k=\"name:bo\" v=\"ཨུ་རུ་སུ།\"/>\n" +
                "            \n" +
                "        <tag k=\"name:bpy\" v=\"রাশিয়া\"/>\n" +
                "            \n" +
                "        <tag k=\"name:br\" v=\"Rusia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:bs\" v=\"Rusija\"/>\n" +
                "            \n" +
                "        <tag k=\"name:bug\" v=\"Russia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:bxr\" v=\"Ородой Холбооной Улас\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ca\" v=\"Rússia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:cbk-zam\" v=\"Rusia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:cdo\" v=\"Ngò̤-lò̤-sṳ̆\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ce\" v=\"Росси\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ceb\" v=\"Rusya\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ch\" v=\"Russia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:chr\" v=\"ᏲᏂᏱ\"/>\n" +
                "            \n" +
                "        <tag k=\"name:chy\" v=\"Russia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ckb\" v=\"ڕووسیا\"/>\n" +
                "            \n" +
                "        <tag k=\"name:co\" v=\"Russia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:crh\" v=\"Rusiye\"/>\n" +
                "            \n" +
                "        <tag k=\"name:cs\" v=\"Rusko\"/>\n" +
                "            \n" +
                "        <tag k=\"name:csb\" v=\"Ruskô\"/>\n" +
                "            \n" +
                "        <tag k=\"name:cu\" v=\"Рѡсїꙗ\"/>\n" +
                "            \n" +
                "        <tag k=\"name:cv\" v=\"Раççей Патшалăхĕ\"/>\n" +
                "            \n" +
                "        <tag k=\"name:cy\" v=\"Rwsia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:da\" v=\"Rusland\"/>\n" +
                "            \n" +
                "        <tag k=\"name:de\" v=\"Russland\"/>\n" +
                "            \n" +
                "        <tag k=\"name:diq\" v=\"Rusya\"/>\n" +
                "            \n" +
                "        <tag k=\"name:dsb\" v=\"Rusojska\"/>\n" +
                "            \n" +
                "        <tag k=\"name:dv\" v=\"ރޫސީވިލާތް\"/>\n" +
                "            \n" +
                "        <tag k=\"name:dz\" v=\"ར་ཤི་ཡཱན་ཕེ་ཌི་རེ་ཤཱན\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ee\" v=\"Russia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:el\" v=\"Ρωσία\"/>\n" +
                "            \n" +
                "        <tag k=\"name:eml\" v=\"Rossia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:en\" v=\"Russia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:eo\" v=\"Rusujo\"/>\n" +
                "            \n" +
                "        <tag k=\"name:es\" v=\"Rusia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:et\" v=\"Venemaa\"/>\n" +
                "            \n" +
                "        <tag k=\"name:eu\" v=\"Errusia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ext\" v=\"Russia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:fa\" v=\"روسیه\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ff\" v=\"Roosiya\"/>\n" +
                "            \n" +
                "        <tag k=\"name:fi\" v=\"Venäjä\"/>\n" +
                "            \n" +
                "        <tag k=\"name:fiu-vro\" v=\"Vinnemaa\"/>\n" +
                "            \n" +
                "        <tag k=\"name:fo\" v=\"Russland\"/>\n" +
                "            \n" +
                "        <tag k=\"name:fr\" v=\"Russie\"/>\n" +
                "            \n" +
                "        <tag k=\"name:frp\" v=\"Russie\"/>\n" +
                "            \n" +
                "        <tag k=\"name:frr\" v=\"Ruslönj\"/>\n" +
                "            \n" +
                "        <tag k=\"name:fur\" v=\"Russie\"/>\n" +
                "            \n" +
                "        <tag k=\"name:fy\" v=\"Ruslân\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ga\" v=\"An Rúis\"/>\n" +
                "            \n" +
                "        <tag k=\"name:gag\" v=\"Rusiya\"/>\n" +
                "            \n" +
                "        <tag k=\"name:gan\" v=\"俄羅斯\"/>\n" +
                "            \n" +
                "        <tag k=\"name:gd\" v=\"An Ruis\"/>\n" +
                "            \n" +
                "        <tag k=\"name:gl\" v=\"Rusia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:gn\" v=\"Rrusia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:gu\" v=\"રશિયા\"/>\n" +
                "            \n" +
                "        <tag k=\"name:gv\" v=\"Yn Roosh\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ha\" v=\"Rasha\"/>\n" +
                "            \n" +
                "        <tag k=\"name:hak\" v=\"Ngò-lò-sṳ̂\"/>\n" +
                "            \n" +
                "        <tag k=\"name:haw\" v=\"Rūsia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:he\" v=\"רוסיה\"/>\n" +
                "            \n" +
                "        <tag k=\"name:hi\" v=\"रूस\"/>\n" +
                "            \n" +
                "        <tag k=\"name:hif\" v=\"Russia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:hr\" v=\"Ruska Federacija\"/>\n" +
                "            \n" +
                "        <tag k=\"name:hsb\" v=\"Ruska federacija\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ht\" v=\"Risi\"/>\n" +
                "            \n" +
                "        <tag k=\"name:hu\" v=\"Oroszország\"/>\n" +
                "            \n" +
                "        <tag k=\"name:hy\" v=\"Ռուսաստան\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ia\" v=\"Russia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:id\" v=\"Rusia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ie\" v=\"Russia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ig\" v=\"Mpaghara Russia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ik\" v=\"Russia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ilo\" v=\"Rusia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:io\" v=\"Rusia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:is\" v=\"Rússland\"/>\n" +
                "            \n" +
                "        <tag k=\"name:it\" v=\"Russia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:iu\" v=\"ᐅᓛᓴ\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ja\" v=\"ロシア\"/>\n" +
                "            \n" +
                "        <tag k=\"name:jbo\" v=\"rukygu'e\"/>\n" +
                "            \n" +
                "        <tag k=\"name:jv\" v=\"Rusia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ka\" v=\"რუსეთი\"/>\n" +
                "            \n" +
                "        <tag k=\"name:kaa\" v=\"Rossiya\"/>\n" +
                "            \n" +
                "        <tag k=\"name:kab\" v=\"Rrus\"/>\n" +
                "            \n" +
                "        <tag k=\"name:kbd\" v=\"Урысей\"/>\n" +
                "            \n" +
                "        <tag k=\"name:kg\" v=\"Rusia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ki\" v=\"Russia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:kk\" v=\"Ресей\"/>\n" +
                "            \n" +
                "        <tag k=\"name:kl\" v=\"Ruslandi\"/>\n" +
                "            \n" +
                "        <tag k=\"name:km\" v=\"រុស្ស៊ី\"/>\n" +
                "            \n" +
                "        <tag k=\"name:kn\" v=\"ರಷ್ಯಾ\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ko\" v=\"러시아\"/>\n" +
                "            \n" +
                "        <tag k=\"name:koi\" v=\"Рочму\"/>\n" +
                "            \n" +
                "        <tag k=\"name:krc\" v=\"Россия Федерация\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ks\" v=\"روٗس\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ku\" v=\"Rûsya\"/>\n" +
                "            \n" +
                "        <tag k=\"name:kv\" v=\"Рочму\"/>\n" +
                "            \n" +
                "        <tag k=\"name:kw\" v=\"Russi\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ky\" v=\"Орусия\"/>\n" +
                "            \n" +
                "        <tag k=\"name:la\" v=\"Russia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:lad\" v=\"Rusia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:lb\" v=\"Russland\"/>\n" +
                "            \n" +
                "        <tag k=\"name:lbe\" v=\"Аьрасат\"/>\n" +
                "            \n" +
                "        <tag k=\"name:lez\" v=\"Урусат\"/>\n" +
                "            \n" +
                "        <tag k=\"name:lg\" v=\"Rwasha\"/>\n" +
                "            \n" +
                "        <tag k=\"name:li\" v=\"Rusland\"/>\n" +
                "            \n" +
                "        <tag k=\"name:lij\" v=\"Ruscia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:lmo\" v=\"Rüssia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ln\" v=\"Rusí\"/>\n" +
                "            \n" +
                "        <tag k=\"name:lo\" v=\"ລັດເຊັຽ\"/>\n" +
                "            \n" +
                "        <tag k=\"name:lt\" v=\"Rusija\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ltg\" v=\"Krīveja\"/>\n" +
                "            \n" +
                "        <tag k=\"name:lv\" v=\"Krievija\"/>\n" +
                "            \n" +
                "        <tag k=\"name:lzh\" v=\"俄羅斯\"/>\n" +
                "            \n" +
                "        <tag k=\"name:map-bms\" v=\"Rusia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:mdf\" v=\"Рузмастор\"/>\n" +
                "            \n" +
                "        <tag k=\"name:mg\" v=\"Rosia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:mhr\" v=\"Россий\"/>\n" +
                "            \n" +
                "        <tag k=\"name:mi\" v=\"Ruhia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:min\" v=\"Rusia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:mk\" v=\"Русија\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ml\" v=\"റഷ്യ\"/>\n" +
                "            \n" +
                "        <tag k=\"name:mn\" v=\"Оросын Холбооны Улс\"/>\n" +
                "            \n" +
                "        <tag k=\"name:mo\" v=\"Русия\"/>\n" +
                "            \n" +
                "        <tag k=\"name:mr\" v=\"रशिया\"/>\n" +
                "            \n" +
                "        <tag k=\"name:mrj\" v=\"Россий\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ms\" v=\"Rusia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:mt\" v=\"Russja\"/>\n" +
                "            \n" +
                "        <tag k=\"name:mwl\" v=\"Rússia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:my\" v=\"ရုရှားနိုင်ငံ\"/>\n" +
                "            \n" +
                "        <tag k=\"name:myv\" v=\"Россия Мастор\"/>\n" +
                "            \n" +
                "        <tag k=\"name:mzn\" v=\"ئوروسیا\"/>\n" +
                "            \n" +
                "        <tag k=\"name:na\" v=\"Ratsiya\"/>\n" +
                "            \n" +
                "        <tag k=\"name:nah\" v=\"Rusia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:nan\" v=\"Lō͘-se-a\"/>\n" +
                "            \n" +
                "        <tag k=\"name:nap\" v=\"Russia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:nds\" v=\"Russ'sche Föderatschoon\"/>\n" +
                "            \n" +
                "        <tag k=\"name:nds-nl\" v=\"Ruslaand\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ne\" v=\"रुस\"/>\n" +
                "            \n" +
                "        <tag k=\"name:new\" v=\"रुस\"/>\n" +
                "            \n" +
                "        <tag k=\"name:nl\" v=\"Russische Federatie\"/>\n" +
                "            \n" +
                "        <tag k=\"name:nn\" v=\"Russland\"/>\n" +
                "            \n" +
                "        <tag k=\"name:no\" v=\"Rusland\"/>\n" +
                "            \n" +
                "        <tag k=\"name:nov\" v=\"Rusia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:nrm\" v=\"Russie\"/>\n" +
                "            \n" +
                "        <tag k=\"name:nso\" v=\"Russia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:nv\" v=\"Biʼééʼ Łichííʼí Bikéyah\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ny\" v=\"Russia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:oc\" v=\"Russia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:om\" v=\"Raashiyaa\"/>\n" +
                "            \n" +
                "        <tag k=\"name:or\" v=\"ଋଷିଆ\"/>\n" +
                "            \n" +
                "        <tag k=\"name:os\" v=\"Уæрæсе\"/>\n" +
                "            \n" +
                "        <tag k=\"name:pa\" v=\"ਰੂਸ\"/>\n" +
                "            \n" +
                "        <tag k=\"name:pag\" v=\"Rusia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:pam\" v=\"Rusia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:pap\" v=\"Rusia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:pcd\" v=\"Russie\"/>\n" +
                "            \n" +
                "        <tag k=\"name:pdc\" v=\"Russland\"/>\n" +
                "            \n" +
                "        <tag k=\"name:pfl\" v=\"Russlond\"/>\n" +
                "            \n" +
                "        <tag k=\"name:pih\" v=\"Rusha\"/>\n" +
                "            \n" +
                "        <tag k=\"name:pl\" v=\"Rosja\"/>\n" +
                "            \n" +
                "        <tag k=\"name:pms\" v=\"Russia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:pnb\" v=\"روس\"/>\n" +
                "            \n" +
                "        <tag k=\"name:pnt\" v=\"Ρουσία\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ps\" v=\"روسیه\"/>\n" +
                "            \n" +
                "        <tag k=\"name:pt\" v=\"Rússia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:qu\" v=\"Rusiya\"/>\n" +
                "            \n" +
                "        <tag k=\"name:rm\" v=\"Russia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:rmy\" v=\"Rusiya\"/>\n" +
                "            \n" +
                "        <tag k=\"name:rn\" v=\"Uburusiya\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ro\" v=\"Rusia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:roa-rup\" v=\"Arusia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:roa-tara\" v=\"Russie\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ru\" v=\"Россия\"/>\n" +
                "            \n" +
                "        <tag k=\"name:rue\" v=\"Росія\"/>\n" +
                "            \n" +
                "        <tag k=\"name:rw\" v=\"Uburusiya\"/>\n" +
                "            \n" +
                "        <tag k=\"name:sah\" v=\"Арассыыйа\"/>\n" +
                "            \n" +
                "        <tag k=\"name:sc\" v=\"Rùssia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:scn\" v=\"Russia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:sco\" v=\"Roushie\"/>\n" +
                "            \n" +
                "        <tag k=\"name:sd\" v=\"روس\"/>\n" +
                "            \n" +
                "        <tag k=\"name:se\" v=\"Ruošša\"/>\n" +
                "            \n" +
                "        <tag k=\"name:sg\" v=\"Rusïi\"/>\n" +
                "            \n" +
                "        <tag k=\"name:sh\" v=\"Rusija\"/>\n" +
                "            \n" +
                "        <tag k=\"name:si\" v=\"රුසියාව\"/>\n" +
                "            \n" +
                "        <tag k=\"name:sk\" v=\"Rusko\"/>\n" +
                "            \n" +
                "        <tag k=\"name:sl\" v=\"Rusija\"/>\n" +
                "            \n" +
                "        <tag k=\"name:sm\" v=\"Lusia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:sn\" v=\"Russia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:so\" v=\"Ruushka\"/>\n" +
                "            \n" +
                "        <tag k=\"name:sq\" v=\"Rusia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:sr\" v=\"Русија\"/>\n" +
                "            \n" +
                "        <tag k=\"name:srn\" v=\"Rusland\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ss\" v=\"IRashiya\"/>\n" +
                "            \n" +
                "        <tag k=\"name:st\" v=\"Russia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:stq\" v=\"Ruslound\"/>\n" +
                "            \n" +
                "        <tag k=\"name:su\" v=\"Rusia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:sv\" v=\"Ryssland\"/>\n" +
                "            \n" +
                "        <tag k=\"name:sw\" v=\"Shirikisho la Urusi\"/>\n" +
                "            \n" +
                "        <tag k=\"name:szl\" v=\"Rusyjo\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ta\" v=\"உருசியா\"/>\n" +
                "            \n" +
                "        <tag k=\"name:te\" v=\"రష్యా\"/>\n" +
                "            \n" +
                "        <tag k=\"name:tet\" v=\"Rúsia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:tg\" v=\"Русия\"/>\n" +
                "            \n" +
                "        <tag k=\"name:th\" v=\"ประเทศรัสเซีย\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ti\" v=\"ራሻ\"/>\n" +
                "            \n" +
                "        <tag k=\"name:tk\" v=\"Russiýa\"/>\n" +
                "            \n" +
                "        <tag k=\"name:tl\" v=\"Pederasyong Ruso\"/>\n" +
                "            \n" +
                "        <tag k=\"name:to\" v=\"Lūsia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:tok\" v=\"ma Losi\"/>\n" +
                "            \n" +
                "        <tag k=\"name:tpi\" v=\"Rasia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:tr\" v=\"Rusya Federasyonu\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ts\" v=\"Russia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:tt\" v=\"Русия\"/>\n" +
                "            \n" +
                "        <tag k=\"name:tum\" v=\"Russia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:tw\" v=\"Russia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ty\" v=\"Rūtia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:tzl\" v=\"Rußía\"/>\n" +
                "            \n" +
                "        <tag k=\"name:udm\" v=\"Россия\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ug\" v=\"روسىيە\"/>\n" +
                "            \n" +
                "        <tag k=\"name:uk\" v=\"Росія\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ur\" v=\"روس\"/>\n" +
                "            \n" +
                "        <tag k=\"name:uz\" v=\"Rossiya Federatsiyasi\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ve\" v=\"Rashia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:vec\" v=\"Rusia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:vep\" v=\"Venäma\"/>\n" +
                "            \n" +
                "        <tag k=\"name:vi\" v=\"Liên bang Nga\"/>\n" +
                "            \n" +
                "        <tag k=\"name:vls\" v=\"Rusland\"/>\n" +
                "            \n" +
                "        <tag k=\"name:vo\" v=\"Rusän\"/>\n" +
                "            \n" +
                "        <tag k=\"name:wa\" v=\"Federåcion d' Rûsseye\"/>\n" +
                "            \n" +
                "        <tag k=\"name:war\" v=\"Rusya\"/>\n" +
                "            \n" +
                "        <tag k=\"name:wo\" v=\"Federaasioŋ bu Riisi\"/>\n" +
                "            \n" +
                "        <tag k=\"name:wuu\" v=\"俄罗斯\"/>\n" +
                "            \n" +
                "        <tag k=\"name:xal\" v=\"Орсн Орн Нутг\"/>\n" +
                "            \n" +
                "        <tag k=\"name:xh\" v=\"IRashiya\"/>\n" +
                "            \n" +
                "        <tag k=\"name:xmf\" v=\"რუსეთი\"/>\n" +
                "            \n" +
                "        <tag k=\"name:yi\" v=\"רוסלאנד\"/>\n" +
                "            \n" +
                "        <tag k=\"name:yo\" v=\"Rọ́síà\"/>\n" +
                "            \n" +
                "        <tag k=\"name:yue\" v=\"俄羅斯\"/>\n" +
                "            \n" +
                "        <tag k=\"name:za\" v=\"Ezlozswh Lienzbangh\"/>\n" +
                "            \n" +
                "        <tag k=\"name:zea\" v=\"Rusland\"/>\n" +
                "            \n" +
                "        <tag k=\"name:zh\" v=\"俄罗斯\"/>\n" +
                "            \n" +
                "        <tag k=\"name:zu\" v=\"IRashiya\"/>\n" +
                "            \n" +
                "        <tag k=\"official_name\" v=\"Российская Федерация\"/>\n" +
                "            \n" +
                "        <tag k=\"official_name:ar\" v=\"الاتحاد الروسي\"/>\n" +
                "            \n" +
                "        <tag k=\"official_name:ast\" v=\"Federación Rusa\"/>\n" +
                "            \n" +
                "        <tag k=\"official_name:cs\" v=\"Ruská federace\"/>\n" +
                "            \n" +
                "        <tag k=\"official_name:de\" v=\"Russische Föderation\"/>\n" +
                "            \n" +
                "        <tag k=\"official_name:en\" v=\"Russian Federation\"/>\n" +
                "            \n" +
                "        <tag k=\"official_name:eo\" v=\"Rusa Federacio\"/>\n" +
                "            \n" +
                "        <tag k=\"official_name:es\" v=\"Federación Rusa\"/>\n" +
                "            \n" +
                "        <tag k=\"official_name:et\" v=\"Venemaa Föderatsioon\"/>\n" +
                "            \n" +
                "        <tag k=\"official_name:fi\" v=\"Venäjän Federaatio\"/>\n" +
                "            \n" +
                "        <tag k=\"official_name:fr\" v=\"Fédération de Russie\"/>\n" +
                "            \n" +
                "        <tag k=\"official_name:pl\" v=\"Federacja Rosyjska\"/>\n" +
                "            \n" +
                "        <tag k=\"official_name:pt\" v=\"Federação Russa\"/>\n" +
                "            \n" +
                "        <tag k=\"official_name:ru\" v=\"Российская Федерация\"/>\n" +
                "            \n" +
                "        <tag k=\"official_name:sk\" v=\"Ruská federácia\"/>\n" +
                "            \n" +
                "        <tag k=\"official_name:sl\" v=\"Ruska federacija\"/>\n" +
                "            \n" +
                "        <tag k=\"official_name:sv\" v=\"Ryska Federationen\"/>\n" +
                "            \n" +
                "        <tag k=\"official_name:uk\" v=\"Російська Федерація\"/>\n" +
                "            \n" +
                "        <tag k=\"official_name:yi\" v=\"רוסישע פֿעדעראַציע\"/>\n" +
                "            \n" +
                "        <tag k=\"old_name\" v=\"Русь;Золотая Орда;Русское Царство;Российская Империя;Союз Советских Социалистических Республик\"/>\n" +
                "            \n" +
                "        <tag k=\"old_name:pl\" v=\"Związek Socjalistycznych Republik Radzieckich\"/>\n" +
                "            \n" +
                "        <tag k=\"old_name:ru\" v=\"Русь;Золотая Орда;Русское Царство;Российская Империя;Союз Советских Социалистических Республик\"/>\n" +
                "            \n" +
                "        <tag k=\"old_short_name\" v=\"СССР\"/>\n" +
                "            \n" +
                "        <tag k=\"old_short_name:en\" v=\"USSR\"/>\n" +
                "            \n" +
                "        <tag k=\"old_short_name:pl\" v=\"ZSRR\"/>\n" +
                "            \n" +
                "        <tag k=\"old_short_name:ru\" v=\"СССР\"/>\n" +
                "            \n" +
                "        <tag k=\"population\" v=\"146880432\"/>\n" +
                "            \n" +
                "        <tag k=\"population:date\" v=\"2018-01-01\"/>\n" +
                "            \n" +
                "        <tag k=\"short_name\" v=\"РФ\"/>\n" +
                "            \n" +
                "        <tag k=\"short_name:ru\" v=\"РФ\"/>\n" +
                "            \n" +
                "        <tag k=\"type\" v=\"boundary\"/>\n" +
                "            \n" +
                "        <tag k=\"wikidata\" v=\"Q159\"/>\n" +
                "            \n" +
                "        <tag k=\"wikipedia\" v=\"ru:Россия\"/>\n" +
                "          \n" +
                "    </area>\n" +
                "      \n" +
                "    <area id=\"3600393980\">\n" +
                "            \n" +
                "        <tag k=\"ISO3166-2\" v=\"RU-KR\"/>\n" +
                "            \n" +
                "        <tag k=\"addr:country\" v=\"RU\"/>\n" +
                "            \n" +
                "        <tag k=\"admin_level\" v=\"4\"/>\n" +
                "            \n" +
                "        <tag k=\"alt_name\" v=\"Карелия\"/>\n" +
                "            \n" +
                "        <tag k=\"alt_name:cs\" v=\"Karelská republika\"/>\n" +
                "            \n" +
                "        <tag k=\"alt_name:sk\" v=\"Karelsko\"/>\n" +
                "            \n" +
                "        <tag k=\"boundary\" v=\"administrative\"/>\n" +
                "            \n" +
                "        <tag k=\"gost_7.67-2003\" v=\"РОФ-КАР\"/>\n" +
                "            \n" +
                "        <tag k=\"int_ref\" v=\"RU-KR\"/>\n" +
                "            \n" +
                "        <tag k=\"is_in:continent\" v=\"Europe\"/>\n" +
                "            \n" +
                "        <tag k=\"is_in:country\" v=\"Russia\"/>\n" +
                "            \n" +
                "        <tag k=\"is_in:country_code\" v=\"RU\"/>\n" +
                "            \n" +
                "        <tag k=\"name\" v=\"Республика Карелия\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ar\" v=\"جمهورية كاريليا\"/>\n" +
                "            \n" +
                "        <tag k=\"name:az\" v=\"Kareliya Respublikası\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ba\" v=\"Карелия\"/>\n" +
                "            \n" +
                "        <tag k=\"name:be\" v=\"Рэспубліка Карэлія\"/>\n" +
                "            \n" +
                "        <tag k=\"name:be-tarask\" v=\"Рэспубліка Карэлія\"/>\n" +
                "            \n" +
                "        <tag k=\"name:br\" v=\"Karelia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ca\" v=\"República de Carèlia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:cs\" v=\"Republika Karélie\"/>\n" +
                "            \n" +
                "        <tag k=\"name:cv\" v=\"Карел Республики\"/>\n" +
                "            \n" +
                "        <tag k=\"name:da\" v=\"Karelske Republik\"/>\n" +
                "            \n" +
                "        <tag k=\"name:de\" v=\"Republik Karelien\"/>\n" +
                "            \n" +
                "        <tag k=\"name:en\" v=\"Republic of Karelia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:es\" v=\"República de Carelia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:et\" v=\"Karjala vabariik\"/>\n" +
                "            \n" +
                "        <tag k=\"name:fi\" v=\"Karjalan tasavalta\"/>\n" +
                "            \n" +
                "        <tag k=\"name:fr\" v=\"République de Carélie\"/>\n" +
                "            \n" +
                "        <tag k=\"name:hu\" v=\"Karélia\"/>\n" +
                "            \n" +
                "        <tag k=\"name:hy\" v=\"Կարելիայի Հանրապետություն\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ka\" v=\"კარელიის რესპუბლიკა\"/>\n" +
                "            \n" +
                "        <tag k=\"name:krl\" v=\"Karjalan tazavaldu\"/>\n" +
                "            \n" +
                "        <tag k=\"name:kv\" v=\"Карелия\"/>\n" +
                "            \n" +
                "        <tag k=\"name:lt\" v=\"Karelijos Respublika\"/>\n" +
                "            \n" +
                "        <tag k=\"name:nl\" v=\"Republiek Karelië\"/>\n" +
                "            \n" +
                "        <tag k=\"name:pl\" v=\"Republika Karelii\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ru\" v=\"Республика Карелия\"/>\n" +
                "            \n" +
                "        <tag k=\"name:sk\" v=\"Karelská republika\"/>\n" +
                "            \n" +
                "        <tag k=\"name:sv\" v=\"Karelska republiken\"/>\n" +
                "            \n" +
                "        <tag k=\"name:tr\" v=\"Karelya Cumhuriyeti\"/>\n" +
                "            \n" +
                "        <tag k=\"name:uk\" v=\"Республіка Карелія\"/>\n" +
                "            \n" +
                "        <tag k=\"name:vep\" v=\"Karjalan Tazovaldkund\"/>\n" +
                "            \n" +
                "        <tag k=\"population\" v=\"634402\"/>\n" +
                "            \n" +
                "        <tag k=\"population:date\" v=\"2014-01-01\"/>\n" +
                "            \n" +
                "        <tag k=\"ref\" v=\"RU-KR\"/>\n" +
                "            \n" +
                "        <tag k=\"short_name:cs\" v=\"Karélie\"/>\n" +
                "            \n" +
                "        <tag k=\"source:name:br\" v=\"ofis publik ar brezhoneg\"/>\n" +
                "            \n" +
                "        <tag k=\"type\" v=\"boundary\"/>\n" +
                "            \n" +
                "        <tag k=\"wikidata\" v=\"Q1914\"/>\n" +
                "            \n" +
                "        <tag k=\"wikipedia\" v=\"ru:Республика Карелия\"/>\n" +
                "          \n" +
                "    </area>\n" +
                "      \n" +
                "    <area id=\"3601020363\">\n" +
                "            \n" +
                "        <tag k=\"addr:country\" v=\"RU\"/>\n" +
                "            \n" +
                "        <tag k=\"admin_level\" v=\"6\"/>\n" +
                "            \n" +
                "        <tag k=\"boundary\" v=\"administrative\"/>\n" +
                "            \n" +
                "        <tag k=\"int_name\" v=\"Lahdenpohja District\"/>\n" +
                "            \n" +
                "        <tag k=\"name\" v=\"Лахденпохский район\"/>\n" +
                "            \n" +
                "        <tag k=\"name:en\" v=\"Lahdenpohja District\"/>\n" +
                "            \n" +
                "        <tag k=\"name:fi\" v=\"Lahdenpohjan piiri\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ru\" v=\"Лахденпохский район\"/>\n" +
                "            \n" +
                "        <tag k=\"official_status\" v=\"ru:муниципальный район\"/>\n" +
                "            \n" +
                "        <tag k=\"population\" v=\"13712\"/>\n" +
                "            \n" +
                "        <tag k=\"population:date\" v=\"2014-01-01\"/>\n" +
                "            \n" +
                "        <tag k=\"type\" v=\"boundary\"/>\n" +
                "            \n" +
                "        <tag k=\"wikidata\" v=\"Q934100\"/>\n" +
                "            \n" +
                "        <tag k=\"wikipedia\" v=\"ru:Лахденпохский район\"/>\n" +
                "          \n" +
                "    </area>\n" +
                "      \n" +
                "    <area id=\"3601020799\">\n" +
                "            \n" +
                "        <tag k=\"admin_level\" v=\"8\"/>\n" +
                "            \n" +
                "        <tag k=\"boundary\" v=\"administrative\"/>\n" +
                "            \n" +
                "        <tag k=\"name\" v=\"Куркиёкское сельское поселение\"/>\n" +
                "            \n" +
                "        <tag k=\"name:fi\" v=\"Kurkijoen kunta\"/>\n" +
                "            \n" +
                "        <tag k=\"official_status\" v=\"ru:сельское поселение\"/>\n" +
                "            \n" +
                "        <tag k=\"type\" v=\"boundary\"/>\n" +
                "            \n" +
                "        <tag k=\"wikidata\" v=\"Q1984178\"/>\n" +
                "            \n" +
                "        <tag k=\"wikipedia\" v=\"ru:Куркиёкское сельское поселение\"/>\n" +
                "          \n" +
                "    </area>\n" +
                "      \n" +
                "    <area id=\"3601216601\">\n" +
                "            \n" +
                "        <tag k=\"addr:country\" v=\"RU\"/>\n" +
                "            \n" +
                "        <tag k=\"admin_level\" v=\"3\"/>\n" +
                "            \n" +
                "        <tag k=\"boundary\" v=\"administrative\"/>\n" +
                "            \n" +
                "        <tag k=\"name\" v=\"Северо-Западный федеральный округ\"/>\n" +
                "            \n" +
                "        <tag k=\"name:be\" v=\"Паўночна-Заходняя федэральная акруга\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ca\" v=\"Districte Federal del Nord-oest\"/>\n" +
                "            \n" +
                "        <tag k=\"name:cs\" v=\"Severozápadní federální okruh\"/>\n" +
                "            \n" +
                "        <tag k=\"name:de\" v=\"Föderationskreis Nordwest\"/>\n" +
                "            \n" +
                "        <tag k=\"name:en\" v=\"Northwestern Federal District\"/>\n" +
                "            \n" +
                "        <tag k=\"name:eo\" v=\"Nordokcidenta federacia regiono\"/>\n" +
                "            \n" +
                "        <tag k=\"name:fi\" v=\"Luoteis-Venäjä\"/>\n" +
                "            \n" +
                "        <tag k=\"name:fr\" v=\"District fédéral du Nord-Ouest\"/>\n" +
                "            \n" +
                "        <tag k=\"name:hu\" v=\"Északnyugati szövetségi körzet\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ja\" v=\"北西連邦管区\"/>\n" +
                "            \n" +
                "        <tag k=\"name:lt\" v=\"Šiaurės-Vakarų federalinė apskritis\"/>\n" +
                "            \n" +
                "        <tag k=\"name:pl\" v=\"Północno-Zachodni Okręg Federalny\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ru\" v=\"Северо-Западный федеральный округ\"/>\n" +
                "            \n" +
                "        <tag k=\"name:sk\" v=\"Severozápadný federálny okruh\"/>\n" +
                "            \n" +
                "        <tag k=\"name:uk\" v=\"Північно-Західний федеральний округ\"/>\n" +
                "            \n" +
                "        <tag k=\"official_status\" v=\"ru:федеральный округ\"/>\n" +
                "            \n" +
                "        <tag k=\"population\" v=\"13717733\"/>\n" +
                "            \n" +
                "        <tag k=\"population:date\" v=\"2013-01-01\"/>\n" +
                "            \n" +
                "        <tag k=\"ref\" v=\"СЗФО\"/>\n" +
                "            \n" +
                "        <tag k=\"type\" v=\"boundary\"/>\n" +
                "            \n" +
                "        <tag k=\"wikidata\" v=\"Q383093\"/>\n" +
                "            \n" +
                "        <tag k=\"wikipedia\" v=\"ru:Северо-Западный федеральный округ\"/>\n" +
                "          \n" +
                "    </area>\n" +
                "      \n" +
                "    <area id=\"3603558373\">\n" +
                "            \n" +
                "        <tag k=\"boundary\" v=\"economic\"/>\n" +
                "            \n" +
                "        <tag k=\"name\" v=\"Северный экономический район\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ca\" v=\"Regió econòmica del Nord\"/>\n" +
                "            \n" +
                "        <tag k=\"name:de\" v=\"Nördliche Wirtschaftsregion\"/>\n" +
                "            \n" +
                "        <tag k=\"name:es\" v=\"Región económica del Norte\"/>\n" +
                "            \n" +
                "        <tag k=\"name:fi\" v=\"Pohjoinen talousalue\"/>\n" +
                "            \n" +
                "        <tag k=\"name:fr\" v=\"Région économique du Nord\"/>\n" +
                "            \n" +
                "        <tag k=\"name:hu\" v=\"Északi Gazdasági Körzet\"/>\n" +
                "            \n" +
                "        <tag k=\"name:pt\" v=\"Região Econômica do Norte\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ru\" v=\"Северный экономический район\"/>\n" +
                "            \n" +
                "        <tag k=\"population\" v=\"4725000\"/>\n" +
                "            \n" +
                "        <tag k=\"type\" v=\"boundary\"/>\n" +
                "            \n" +
                "        <tag k=\"wikidata\" v=\"Q856932\"/>\n" +
                "            \n" +
                "        <tag k=\"wikipedia\" v=\"ru:Северный экономический район\"/>\n" +
                "          \n" +
                "    </area>\n" +
                "      \n" +
                "    <area id=\"3603563652\">\n" +
                "            \n" +
                "        <tag k=\"alt_name\" v=\"UTC+3\"/>\n" +
                "            \n" +
                "        <tag k=\"boundary\" v=\"timezone\"/>\n" +
                "            \n" +
                "        <tag k=\"name\" v=\"Московское время\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ar\" v=\"توقيت موسكو\"/>\n" +
                "            \n" +
                "        <tag k=\"name:av\" v=\"Москваялъул заман\"/>\n" +
                "            \n" +
                "        <tag k=\"name:be\" v=\"Маскоўскі час\"/>\n" +
                "            \n" +
                "        <tag k=\"name:cs\" v=\"Moskevský čas\"/>\n" +
                "            \n" +
                "        <tag k=\"name:de\" v=\"Moskauer Zeit\"/>\n" +
                "            \n" +
                "        <tag k=\"name:en\" v=\"Moscow Time\"/>\n" +
                "            \n" +
                "        <tag k=\"name:fi\" v=\"Moskovan aika\"/>\n" +
                "            \n" +
                "        <tag k=\"name:hu\" v=\"Moszkvai idő\"/>\n" +
                "            \n" +
                "        <tag k=\"name:lt\" v=\"Maskvos laikas\"/>\n" +
                "            \n" +
                "        <tag k=\"name:pl\" v=\"Czas moskiewski\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ru\" v=\"Московское время\"/>\n" +
                "            \n" +
                "        <tag k=\"name:tt\" v=\"Мәскәү вакыты\"/>\n" +
                "            \n" +
                "        <tag k=\"name:udm\" v=\"Муско дыр\"/>\n" +
                "            \n" +
                "        <tag k=\"name:uk\" v=\"Московський час\"/>\n" +
                "            \n" +
                "        <tag k=\"ref\" v=\"MSK\"/>\n" +
                "            \n" +
                "        <tag k=\"timezone\" v=\"Europe/Moscow\"/>\n" +
                "            \n" +
                "        <tag k=\"type\" v=\"boundary\"/>\n" +
                "            \n" +
                "        <tag k=\"wikidata\" v=\"Q842320\"/>\n" +
                "            \n" +
                "        <tag k=\"wikipedia\" v=\"ru:Московское время\"/>\n" +
                "          \n" +
                "    </area>\n" +
                "      \n" +
                "    <area id=\"3603564026\">\n" +
                "            \n" +
                "        <tag k=\"alt_name\" v=\"Объединённое стратегическое командование &quot;Запад&quot;\"/>\n" +
                "            \n" +
                "        <tag k=\"boundary\" v=\"military_district\"/>\n" +
                "            \n" +
                "        <tag k=\"name\" v=\"Западный военный округ\"/>\n" +
                "            \n" +
                "        <tag k=\"name:de\" v=\"Westlicher Militärbezirk\"/>\n" +
                "            \n" +
                "        <tag k=\"name:en\" v=\"Western Military District\"/>\n" +
                "            \n" +
                "        <tag k=\"name:fi\" v=\"Läntinen sotilaspiiri\"/>\n" +
                "            \n" +
                "        <tag k=\"name:lt\" v=\"Vakarų karinė apygarda\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ru\" v=\"Западный военный округ\"/>\n" +
                "            \n" +
                "        <tag k=\"name:uk\" v=\"Західний військовий округ\"/>\n" +
                "            \n" +
                "        <tag k=\"ref\" v=\"ЗВО\"/>\n" +
                "            \n" +
                "        <tag k=\"short_name\" v=\"ЗВО\"/>\n" +
                "            \n" +
                "        <tag k=\"source\" v=\"wikipedia\"/>\n" +
                "            \n" +
                "        <tag k=\"type\" v=\"boundary\"/>\n" +
                "            \n" +
                "        <tag k=\"wikidata\" v=\"Q2566413\"/>\n" +
                "            \n" +
                "        <tag k=\"wikipedia\" v=\"ru:Западный военный округ (Россия)\"/>\n" +
                "          \n" +
                "    </area>\n" +
                "      \n" +
                "    <area id=\"3608285930\">\n" +
                "            \n" +
                "        <tag k=\"boundary\" v=\"protected_area\"/>\n" +
                "            \n" +
                "        <tag k=\"legal:ru\" v=\"Постановление правительства Российской Федерации от 28.12.2017 №1684 «О создании национального парка „Ладожские шхеры“»\"/>\n" +
                "            \n" +
                "        <tag k=\"leisure\" v=\"nature_reserve\"/>\n" +
                "            \n" +
                "        <tag k=\"name\" v=\"Национальный парк «Ладожские шхеры»\"/>\n" +
                "            \n" +
                "        <tag k=\"name:en\" v=\"Lagoda Skerries National Park\"/>\n" +
                "            \n" +
                "        <tag k=\"name:fi\" v=\"Laatokan saariston kansallispuisto\"/>\n" +
                "            \n" +
                "        <tag k=\"name:ru\" v=\"Национальный парк «Ладожские шхеры»\"/>\n" +
                "            \n" +
                "        <tag k=\"protect_class\" v=\"2\"/>\n" +
                "            \n" +
                "        <tag k=\"protection_title\" v=\"национальный парк\"/>\n" +
                "            \n" +
                "        <tag k=\"source\" v=\"http://oopt.aari.ru/oopt/%D0%9B%D0%B0%D0%B4%D0%BE%D0%B6%D1%81%D0%BA%D0%B8%D0%B5-%D1%88%D1%85%D0%B5%D1%80%D1%8B/\"/>\n" +
                "            \n" +
                "        <tag k=\"start_date\" v=\"2017\"/>\n" +
                "            \n" +
                "        <tag k=\"type\" v=\"boundary\"/>\n" +
                "            \n" +
                "        <tag k=\"wikidata\" v=\"Q4252610\"/>\n" +
                "          \n" +
                "    </area>\n" +
                "    \n" +
                "</osm>\n";
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(testStr));
            Document document = documentBuilder.parse(is);
            Node root =  document.getDocumentElement();
//            System.out.println(root.getFirstChild());
//            System.out.println(root.getBaseURI());
//            System.out.println(root.getChildNodes());

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }

        return true;
    }

    @Override
    public Integer getIslandID(Point point) {
        return null;
    }

    @Override
    public Point getIslandCoordinates(String name) {
        return null;
    }

    @Override
    public Point getIslandCoordinates(Integer ID) {
        return null;
    }

    @Override
    public List<Point> getWay(Point A, Point B) {
        return null;
    }
}