package com.pnfsoftware.jeb.corei.parsers.xml;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitContribution;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.RuntimeProjectUtil;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.output.IUnitFormatter;
import com.pnfsoftware.jeb.core.output.UnitFormatterUtil;
import com.pnfsoftware.jeb.core.output.text.TextDocumentUtil;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractBinaryUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.IXmlUnit;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.android.IApkUnit;
import com.pnfsoftware.jeb.util.base.SystemUtil;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.encoding.xml.XDocument;
import com.pnfsoftware.jeb.util.encoding.xml.XmlParser;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.format.XercesUpdater;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.net.Net;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jebglobal.Nj;
import com.pnfsoftware.jebglobal.aB;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

@Ser
public class cq extends AbstractBinaryUnit implements IXmlUnit, aB {
   private static final ILogger pC = GlobalLog.getLogger(cq.class);
   @SerTransient
   private Document A;
   @SerTransient
   private IUnitContribution kS;
   @SerTransient
   private String wS;
   @SerTransient
   private String UT;
   @SerTransient
   private boolean E;
   @SerTransient
   private boolean sY;
   @SerTransient
   private String ys;
   @SerTransient
   private List ld;

   public cq(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super("application/xml", var2, "xml", var1, var3, var4, var5);
   }

   @Override
   public boolean isProcessed() {
      return super.isProcessed() && (this.A != null || this.UT != null);
   }

   @Override
   public boolean pC() {
      return this.E;
   }

   private int A() {
      try {
         int var2;
         try (cq.Av var1 = new cq.Av(this.getInput().getStream())) {
            var2 = Strings.getInitialBlankSize(var1, true);
         }

         return var2;
      } catch (IOException var6) {
         return 0;
      }
   }

   @Override
   protected boolean processInternal() {
      int var1 = this.A();
      boolean var2 = this.A(var1, false);
      if (!var2) {
         var2 = this.pC(var1, true);
      }

      if (!var2) {
         try (InputStream var3 = this.getInput().getStream()) {
            byte[] var4 = IO.readInputStream(var3);
            this.UT = "# Failed to parse as XML:\n\n" + Strings.decodeUTF8(var4);
            var2 = true;
            this.E = true;
         } catch (Exception var8) {
         }
      }

      byte[] var9 = XmlIdentifier.getNonWhitespaceHeader(this.getInput(), 5, true);
      this.sY = XmlIdentifier.pC(var9);
      return var2;
   }

   private boolean pC(int var1, boolean var2) {
      byte[] var3;
      try (cq.Av var4 = new cq.Av(this.getInput().getStream())) {
         var4.skip(var1);
         var3 = IO.readInputStream(var4);
      } catch (Exception var9) {
         return false;
      }

      try {
         XmlParser var11 = new XmlParser();
         var11.setAssignParentNodes(true);
         var11.setSortAttributes(true);
         var11.setHandleBackslashAxmlStyle(true);
         var11.setAllowUnclosedTags(true);
         var11.setAllowMismatchedTags(true);
         var11.setAllowNoXmlDeclaration(true);
         this.A = var11.parse(var3);
         this.ld = com.pnfsoftware.jeb.corei.parsers.xml.Av.pC(this.A);
         this.ys = this.A.getFirstChild().getNodeName();
         return true;
      } catch (Exception var10) {
         if (var2) {
            this.pC(new RuntimeException("XML parsing v2 failed", var10), Formatter.escapeBytes(var3));
         }

         return false;
      }
   }

   private boolean A(int var1, boolean var2) {
      DocumentBuilder var3;
      try {
         DocumentBuilderFactory var4 = DocumentBuilderFactory.newInstance();
         var3 = var4.newDocumentBuilder();
      } catch (ParserConfigurationException var12) {
         pC.catching(var12);
         JebCoreService.notifySilentExceptionToClient(var12);
         return false;
      }

      try {
         try (cq.Av var18 = new cq.Av(this.getInput().getStream())) {
            if (var1 > 0) {
               var18.skip(var1);
            }

            this.A = var3.parse(var18);
            this.A.normalize();
            this.ld = com.pnfsoftware.jeb.corei.parsers.xml.Av.pC(this.A);
            this.ys = this.A.getFirstChild().getNodeName();
         }

         return true;
      } catch (IOException var16) {
         pC.catchingSilent(var16);
         return false;
      } catch (SAXException var17) {
         this.logError(true, S.L("Improper XML"));
         pC.catchingSilent(var17);
         boolean var5 = pC(var17.getMessage());
         if (var5 && var2) {
            String var6;
            if (this.getInput().getCurrentSize() > 65536L) {
               var6 = Strings.ff("{{ Too Large: %d bytes }}", this.getInput().getCurrentSize());
            } else {
               try (InputStream var7 = this.getInput().getStream()) {
                  var6 = Strings.decodeUTF8(IO.readInputStream(var7));
               } catch (IOException var14) {
                  var6 = Strings.ff("{{ Exception: %s }}", var14);
               }
            }

            this.pC(var17, var6);
         }

         return false;
      }
   }

   @Override
   public Document getDocument() {
      return this.pC(true);
   }

   public Document pC(boolean var1) {
      if (var1 && !(this.A instanceof XDocument)) {
         Document var2 = (Document)this.A.cloneNode(true);
         return var2;
      } else {
         return this.A;
      }
   }

   @Override
   public boolean hasXmlDeclaration() {
      return this.sY;
   }

   @Override
   public String getDocumentAsText() {
      if (this.A != null) {
         Sv var1 = new Sv(this);
         String var2 = TextDocumentUtil.buildText(var1).toString();
         var1.dispose();
         return var2;
      } else {
         return this.UT != null ? this.UT : null;
      }
   }

   @Override
   public IUnitFormatter getFormatter() {
      IUnitFormatter var1 = super.getFormatter();
      if ((this.A != null || this.UT != null) && UnitFormatterUtil.getPresentationByIdentifier(var1, 1L) == null) {
         var1.addPresentation(new DH(this, 1L, S.L("Formatted Text"), true), false);
      }

      return var1;
   }

   @Override
   public List getContributions() {
      List var1 = super.getContributions();
      if (this.kS == null && this.wS == null && this.A != null) {
         IApkUnit var2 = (IApkUnit)UnitUtil.findAncestor(this, IApkUnit.class, false);
         if (var2 != null) {
            boolean var3 = this.wS();
            boolean var4 = var3 ? false : this.kS();
            if (!var3 && !var4) {
               this.wS = "apk.unknown";
            } else {
               this.wS = "apk.xml";
               String var5 = var2.getPropertyManager().getString("AndroidJavadocRoot");
               if (Strings.isBlank(var5)) {
                  pC.debug("This javadoc root location is illegal: %s", var5);
                  this.wS = "apk.misconfigured";
                  return var1;
               }

               try {
                  Net var6 = ((Nj)RuntimeProjectUtil.findProject(this).getEnginesContext()).kS();
                  if (var3) {
                     this.kS = new com.pnfsoftware.jeb.corei.parsers.dex.K(this, var5, var6);
                     var1.add(this.kS);
                  } else if (var4) {
                     this.kS = com.pnfsoftware.jeb.corei.parsers.dex.Ws.pC(this, var5, var6);
                     if (this.kS != null) {
                        var1.add(this.kS);
                     }
                  }
               } catch (Exception var7) {
                  pC.error(
                     S.L("The contribution for an XML unit cannot be created.\nPlease update your properties, this javadoc root location seems illegal: %s"),
                     var5
                  );
                  pC.catching(var7);
               }
            }
         } else {
            this.wS = "unknown";
         }
      }

      return var1;
   }

   private boolean kS() {
      return this.A("resources");
   }

   private boolean wS() {
      return this.A("manifest");
   }

   private boolean A(String var1) {
      return var1 != null && var1.equals(this.ys);
   }

   private Node pC(long var1) {
      if (this.A == null) {
         return null;
      } else {
         long var3 = var1 >>> 8;
         return var3 != 0L && var3 < this.ld.size() ? (Node)this.ld.get((int)var3) : null;
      }
   }

   @Override
   public Object getItemObject(long var1) {
      if (this.A == null) {
         return null;
      } else {
         Node var3 = this.pC(var1);
         if (var3 == null) {
            return null;
         } else {
            boolean var4 = (var1 & 128L) == 0L;
            if (var4) {
               long var5 = (var1 & 126L) >>> 1;
               synchronized (var3) {
                  NamedNodeMap var8 = var3.getAttributes();
                  if (var5 < var8.getLength()) {
                     Node var9 = var8.item((int)var5);
                     if ((var1 & 1L) == 0L) {
                        return var9;
                     }

                     if (var9 instanceof Attr) {
                        return new Object[]{var9, ((Attr)var9).getValue()};
                     }
                  }

                  return null;
               }
            } else {
               return var3;
            }
         }
      }
   }

   @Override
   public boolean isValidAddress(String var1) {
      return this.getItemAtAddress(var1) != 0L;
   }

   @Override
   public String getAddressOfItem(long var1) {
      Node var3 = this.pC(var1);
      if (var3 == null) {
         return null;
      } else {
         StringBuilder var4 = new StringBuilder();

         do {
            var4.insert(0, var3.getNodeName()).insert(0, '/');
            var3 = var3.getParentNode();
            if (var3 == null) {
               return null;
            }
         } while (var3 != this.A);

         return var4.toString();
      }
   }

   @Override
   public List getRelatedItems(long var1) {
      return Collections.emptyList();
   }

   @Override
   public long getItemAtAddress(String var1) {
      return 0L;
   }

   @Override
   public Map getAddressLabels() {
      return null;
   }

   @Override
   public String getAddressLabel(String var1) {
      return null;
   }

   public static boolean pC(String var0) {
      if (var0.contains("already specified for element")) {
         return false;
      } else {
         if (var0.contains("An invalid XML character")) {
            int var1 = var0.indexOf("(Unicode: 0x");
            if (var1 >= 0) {
               var1 += 12;
               int var2 = var0.indexOf(")", var1);
               if (var2 >= 0) {
                  int var3 = Conversion.stringToInt(var0.substring(var1, var2), 0, 16);
                  if (var3 < 32) {
                     return false;
                  }
               }
            }
         }

         return true;
      }
   }

   private void pC(Exception var1, String var2) {
      HashMap var3 = new HashMap();
      var3.put("unit-path", UnitUtil.buildFullyQualifiedUnitPath(this));
      var3.put("failedXmlData", var2);
      JebCoreService.notifySilentExceptionToClient(var1, var3);
   }

   static {
      if (SystemUtil.getMajorJavaVersion() <= 8) {
         int var0 = new XercesUpdater().update();
         Object[] var10000 = new Object[]{var0};
      }
   }

   private static class Av extends FilterInputStream {
      protected Av(InputStream var1) {
         super(var1);
      }

      @Override
      public int read() throws IOException {
         int var1 = super.read();
         return var1 == 0 ? 32 : var1;
      }

      @Override
      public int read(byte[] var1, int var2, int var3) throws IOException {
         int var4 = super.read(var1, var2, var3);

         for (int var5 = var2; var5 < var2 + var3; var5++) {
            if (var1[var5] == 0) {
               var1[var5] = 32;
            }
         }

         return var4;
      }
   }
}
