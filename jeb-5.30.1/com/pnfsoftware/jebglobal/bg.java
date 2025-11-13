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

public class bg {
   private static final ILogger q = GlobalLog.getLogger(bg.class);
   private String RF;
   private long xK;
   private long Dw;

   public bg(String var1, long var2) {
      this.RF = var1;
      this.xK = var2;
   }

   public String q() {
      return this.RF;
   }

   public long RF() {
      return this.xK;
   }

   public long xK() {
      return this.Dw;
   }

   @Override
   public String toString() {
      return Strings.ff("%s @ %Xh (DT: %Xh)", this.q(), this.RF(), this.xK());
   }

   public static List q(String var0) {
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
            bg var14 = new bg(var23, 0L);
            var14.xK = var24;
            var14.Dw = var12;
            var1.add(var14);
         }

         return var1;
      } catch (SAXException | IOException | ParserConfigurationException var15) {
         q.catching(var15);
         var1.clear();
         String var2 = "<library name=\"([^\"]+)\".*l_addr=\"0x([^\"]+)\".*l_ld=\"0x([^\"]+)\".*";
         Pattern var3 = Pattern.compile(var2);

         for (String var7 : var0.split(">")) {
            Matcher var8 = var3.matcher(var7);
            if (var8.matches()) {
               String var9 = var8.group(1);
               bg var10 = new bg(var9, 0L);
               var10.xK = Conversion.stringToLong("0x" + var8.group(2));
               var10.Dw = Conversion.stringToLong("0x" + var8.group(3));
               var1.add(var10);
            }
         }

         return var1;
      }
   }
}
