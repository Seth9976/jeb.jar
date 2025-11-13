package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterDescriptionEntry;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class hq {
   private static final ILogger pC = GlobalLog.getLogger(hq.class);
   private oi A;
   private List kS = new ArrayList();
   private String wS;
   private String UT;
   private Ti E;

   public hq(oi var1) throws IOException, Ae {
      this.A = var1;
      this.E = new Ti(false);
      this.A("target.xml");
      this.E.pC();
   }

   private void A(String var1) throws IOException, Ae {
      try {
         String var2 = this.A.pC(var1);
         DocumentBuilderFactory var3 = DocumentBuilderFactory.newInstance();
         DocumentBuilder var4 = var3.newDocumentBuilder();
         var4.setEntityResolver(new Ff(this));
         Document var5 = var4.parse(new ByteArrayInputStream(Strings.encodeUTF8(var2)));
         var5.normalize();
         Object var6 = var5;
         if ("target.xml".equals(var1)) {
            var6 = var5.getElementsByTagName("target").item(0);
         }

         this.pC((Node)var6);
      } catch (SAXException | ParserConfigurationException var7) {
         throw new Ae(var7);
      }
   }

   private void pC(Node var1) throws IOException, Ae {
      NodeList var2 = var1.getChildNodes();

      for (int var3 = 0; var3 < var2.getLength(); var3++) {
         Node var4 = var2.item(var3);
         if (var4.getNodeType() == 1) {
            String var5 = var4.getNodeName();
            if (var5 != null) {
               if (var5.equals("architecture")) {
                  this.wS = var4.getTextContent();
               } else if (var5.equals("osabi")) {
                  this.UT = var4.getTextContent();
               } else if (var5.equals("xi:include")) {
                  String var18 = var4.getAttributes().getNamedItem("href").getNodeValue();
                  this.A(var18);
               } else if (var5.equals("feature")) {
                  Node var6 = var4.getAttributes().getNamedItem("name");
                  if (var6 != null) {
                     String var7 = var6.getNodeValue();
                     this.kS.add(var7);
                  }

                  NodeList var19 = var4.getChildNodes();

                  for (int var8 = 0; var8 < var19.getLength(); var8++) {
                     Node var9 = var19.item(var8);
                     if (var9.getNodeType() == 1 && "reg".equals(var9.getNodeName())) {
                        NamedNodeMap var10 = var9.getAttributes();
                        Node var11 = var10.getNamedItem("regnum");
                        int var12 = var11 == null ? -1 : zI.pC(var11.getNodeValue(), 10);
                        Node var13 = var10.getNamedItem("name");
                        String var14 = var13 == null ? "" : var13.getNodeValue();
                        Node var15 = var10.getNamedItem("bitsize");
                        int var16 = var15 == null ? 0 : zI.pC(var15.getNodeValue(), 10);
                        RegisterDescriptionEntry var17 = this.E.pC(var12, var16, var14, null, null);
                        Object[] var10000 = new Object[]{var17};
                     }
                  }
               }
            }
         }
      }
   }

   public String pC() {
      return this.wS;
   }

   public List A() {
      return Collections.unmodifiableList(this.kS);
   }

   public boolean pC(String var1) {
      return this.kS.contains(var1);
   }

   public Ti kS() {
      return this.E;
   }

   public ProcessorType wS() {
      if (this.pC("org.gnu.gdb.arm.core")) {
         return ProcessorType.ARM;
      } else if (this.pC("org.gnu.gdb.aarch64.core")) {
         return ProcessorType.ARM64;
      } else if (this.pC("org.gnu.gdb.i386.core")) {
         return Strings.equals(this.pC(), "i386:x86-64") ? ProcessorType.X86_64 : ProcessorType.X86;
      } else {
         return this.pC("org.gnu.gdb.mips.linux") ? ProcessorType.MIPS : null;
      }
   }
}
