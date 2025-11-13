package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class cR {
   private static final ILogger pC = GlobalLog.getLogger(cR.class);
   private aI A;

   cR(aI var1) {
      this.A = var1;
   }

   public List pC(String var1) {
      ArrayList var2 = new ArrayList();

      try {
         DocumentBuilderFactory var17 = DocumentBuilderFactory.newInstance();
         DocumentBuilder var18 = var17.newDocumentBuilder();
         Document var19 = var18.parse(new ByteArrayInputStream(Strings.encodeUTF8(var1)));
         var19.normalize();
         NodeList var20 = var19.getElementsByTagName("thread");

         for (int var21 = 0; var21 < var20.getLength(); var21++) {
            Node var22 = var20.item(var21);
            NamedNodeMap var23 = var22.getAttributes();
            String var24 = var23.getNamedItem("id").getNodeValue();
            int[] var25 = zI.gp(var24);
            int var26 = var25[1];
            String var27 = null;
            if (var23.getNamedItem("name") != null) {
               var27 = var23.getNamedItem("name").getNodeValue();
            }

            Dn var14 = new Dn(var26, var27);
            var2.add(var14);
         }

         return var2;
      } catch (SAXException | IOException | ParserConfigurationException var16) {
         pC.catching(var16);
         var2.clear();

         try {
            String var3 = "<thread id=\"([^\"]+)\".*core=\"([^\"]+)\".*";
            Pattern var4 = Pattern.compile(var3);

            for (String var8 : var1.split(">\n")) {
               Matcher var9 = var4.matcher(var8);
               if (var9.matches()) {
                  String var10 = var9.group(1);
                  int[] var11 = zI.gp(var10);
                  int var12 = var11[1];
                  Dn var13 = new Dn(var12, var10);
                  var2.add(var13);
               }
            }

            return var2;
         } catch (IOException var15) {
            pC.catching(var15);
            return null;
         }
      }
   }

   public List A(String var1) {
      ArrayList var2 = new ArrayList();

      try {
         Object var3 = new JSONParser().parse(var1);

         for (Object var6 : (JSONArray)var3) {
            Dn var7 = this.pC((JSONObject)var6);
            if (var7 != null) {
               var2.add(var7);
            }
         }

         return var2;
      } catch (ParseException var8) {
         pC.catching(var8);
         return null;
      }
   }

   public List kS(String var1) {
      return this.A(var1);
   }

   private Dn pC(JSONObject var1) {
      try {
         long var3 = (Long)var1.get("tid");
         String var5 = (String)var1.get("reason");
         String var6 = (String)var1.get("name");
         Tl var7 = null;
         Map var8 = this.A(var1);
         if (var8 != null) {
            var7 = this.A.pC(var8);
         }

         return new Dn((int)var3, var6, var5, var7);
      } catch (Exception var9) {
         pC.catchingSilent(var9);
         return null;
      }
   }

   private Map A(JSONObject var1) {
      try {
         HashMap var2 = new HashMap();
         JSONObject var3 = (JSONObject)var1.get("registers");
         if (var3 != null) {
            for (Object var5 : var3.entrySet()) {
               Entry var6 = (Entry)var5;
               int var7 = Integer.parseInt((String)var6.getKey());
               byte[] var8 = Formatter.hexStringToByteArray((String)var6.getValue());
               var2.put(var7, var8);
            }
         }

         return var2;
      } catch (Exception var9) {
         pC.catchingSilent(var9);
         return null;
      }
   }
}
