package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class JE {
   private static final ILogger pC = GlobalLog.getLogger(JE.class);
   private String A;
   private long kS;
   private long wS;

   public JE(String var1, long var2) {
      this.A = var1;
      this.kS = var2;
   }

   public String pC() {
      return this.A;
   }

   public long A() {
      return this.kS;
   }

   public long kS() {
      return this.wS;
   }

   @Override
   public String toString() {
      return Strings.ff("%s @ %Xh (DT: %Xh)", this.pC(), this.A(), this.kS());
   }

   public static List pC(String var0) {
      ArrayList var1 = new ArrayList();

      try {
         DocumentBuilderFactory var16 = DocumentBuilderFactory.newInstance();
         DocumentBuilder var17 = var16.newDocumentBuilder();
         Document var18 = var17.parse(new ByteArrayInputStream(Strings.encodeUTF8(var0)));
         var18.normalize();
         NodeList var19 = var18.getElementsByTagName("library");

         for (int var20 = 0; var20 < var19.getLength(); var20++) {
            Node var21 = var19.item(var20);
            NamedNodeMap var22 = var21.getAttributes();
            String var23 = var22.getNamedItem("name").getNodeValue();
            long var24 = Conversion.stringToLong(var22.getNamedItem("l_addr").getNodeValue());
            long var12 = Conversion.stringToLong(var22.getNamedItem("l_ld").getNodeValue());
            JE var14 = new JE(var23, 0L);
            var14.kS = var24;
            var14.wS = var12;
            var1.add(var14);
         }

         return var1;
      } catch (SAXException | IOException | ParserConfigurationException var15) {
         pC.catching(var15);
         var1.clear();
         String var2 = "<library name=\"([^\"]+)\".*l_addr=\"0x([^\"]+)\".*l_ld=\"0x([^\"]+)\".*";
         Pattern var3 = Pattern.compile(var2);

         for (String var7 : var0.split(">")) {
            Matcher var8 = var3.matcher(var7);
            if (var8.matches()) {
               String var9 = var8.group(1);
               JE var10 = new JE(var9, 0L);
               var10.kS = Conversion.stringToLong("0x" + var8.group(2));
               var10.wS = Conversion.stringToLong("0x" + var8.group(3));
               var1.add(var10);
            }
         }

         return var1;
      }
   }
}
