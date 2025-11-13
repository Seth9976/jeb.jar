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

public class YF {
   private static final ILogger xK = GlobalLog.getLogger(YF.class);
   static final String q = "target.xml";
   static final String RF = "gdb-target.dtd";
   private nO Dw;
   private List Uv = new ArrayList();
   private String oW;
   private String gO;
   private pF nf;

   public YF(nO var1) throws IOException, WI {
      this.Dw = var1;
      this.nf = new pF(false);
      this.RF("target.xml");
      this.nf.q();
   }

   private void RF(String var1) throws IOException, WI {
      try {
         String var2 = this.Dw.q(var1);
         DocumentBuilderFactory var3 = DocumentBuilderFactory.newInstance();
         DocumentBuilder var4 = var3.newDocumentBuilder();
         var4.setEntityResolver(new rP(this));
         Document var5 = var4.parse(new ByteArrayInputStream(Strings.encodeUTF8(var2)));
         var5.normalize();
         Object var6 = var5;
         if ("target.xml".equals(var1)) {
            var6 = var5.getElementsByTagName("target").item(0);
         }

         this.q((Node)var6);
      } catch (SAXException | ParserConfigurationException var7) {
         throw new WI(var7);
      }
   }

   private void q(Node var1) throws IOException, WI {
      NodeList var2 = var1.getChildNodes();

      for (int var3 = 0; var3 < var2.getLength(); var3++) {
         Node var4 = var2.item(var3);
         if (var4.getNodeType() == 1) {
            String var5 = var4.getNodeName();
            if (var5 != null) {
               if (var5.equals("architecture")) {
                  this.oW = var4.getTextContent();
               } else if (var5.equals("osabi")) {
                  this.gO = var4.getTextContent();
               } else if (var5.equals("xi:include")) {
                  String var18 = var4.getAttributes().getNamedItem("href").getNodeValue();
                  this.RF(var18);
               } else if (var5.equals("feature")) {
                  Node var6 = var4.getAttributes().getNamedItem("name");
                  if (var6 != null) {
                     String var7 = var6.getNodeValue();
                     this.Uv.add(var7);
                  }

                  NodeList var19 = var4.getChildNodes();

                  for (int var8 = 0; var8 < var19.getLength(); var8++) {
                     Node var9 = var19.item(var8);
                     if (var9.getNodeType() == 1 && "reg".equals(var9.getNodeName())) {
                        NamedNodeMap var10 = var9.getAttributes();
                        Node var11 = var10.getNamedItem("regnum");
                        int var12 = var11 == null ? -1 : MO.q(var11.getNodeValue(), 10);
                        Node var13 = var10.getNamedItem("name");
                        String var14 = var13 == null ? "" : var13.getNodeValue();
                        Node var15 = var10.getNamedItem("bitsize");
                        int var16 = var15 == null ? 0 : MO.q(var15.getNodeValue(), 10);
                        RegisterDescriptionEntry var17 = this.nf.q(var12, var16, var14, null, null);
                        Object[] var10000 = new Object[]{var17};
                     }
                  }
               }
            }
         }
      }
   }

   public String q() {
      return this.oW;
   }

   public String RF() {
      return this.gO;
   }

   public List xK() {
      return Collections.unmodifiableList(this.Uv);
   }

   public boolean q(String var1) {
      return this.Uv.contains(var1);
   }

   public pF Dw() {
      return this.nf;
   }

   public ProcessorType Uv() {
      if (this.q("org.gnu.gdb.arm.core")) {
         return ProcessorType.ARM;
      } else if (this.q("org.gnu.gdb.aarch64.core")) {
         return ProcessorType.ARM64;
      } else if (this.q("org.gnu.gdb.i386.core")) {
         return Strings.equals(this.q(), "i386:x86-64") ? ProcessorType.X86_64 : ProcessorType.X86;
      } else {
         return this.q("org.gnu.gdb.mips.linux") ? ProcessorType.MIPS : null;
      }
   }
}
