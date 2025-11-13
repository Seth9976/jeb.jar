package com.pnfsoftware.jeb.corei.parsers.xml;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitContribution;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.RuntimeProjectUtil;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.output.IUnitFormatter;
import com.pnfsoftware.jeb.core.output.UnitFormatterUtil;
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
import com.pnfsoftware.jebglobal.qa;
import com.pnfsoftware.jebglobal.zJ;
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
public class Nt extends AbstractBinaryUnit implements IXmlUnit, qa {
   private static final ILogger xK = GlobalLog.getLogger(Nt.class);
   public static final int q = 1;
   public static final boolean RF = true;
   @SerTransient
   private Document Dw;
   @SerTransient
   private IUnitContribution Uv;
   @SerTransient
   private String oW;
   @SerTransient
   private String gO;
   @SerTransient
   private boolean nf;
   @SerTransient
   private boolean gP;
   @SerTransient
   private String za;
   @SerTransient
   private List lm;

   public Nt(String var1, IInput var2, IUnitProcessor var3, IUnitCreator var4, IPropertyDefinitionManager var5) {
      super("application/xml", var2, "xml", var1, var3, var4, var5);
   }

   @Override
   public boolean isProcessed() {
      return super.isProcessed() && (this.Dw != null || this.gO != null);
   }

   @Override
   public boolean q() {
      return this.nf;
   }

   private int RF() {
      try {
         int var2;
         try (Nt.eo var1 = new Nt.eo(this.getInput().getStream())) {
            var2 = Strings.getInitialBlankSize(var1, true);
         }

         return var2;
      } catch (IOException var6) {
         return 0;
      }
   }

   @Override
   protected boolean processInternal() {
      int var1 = this.RF();
      boolean var2 = this.RF(var1, false);
      if (!var2) {
         var2 = this.q(var1, true);
      }

      if (!var2) {
         try (InputStream var3 = this.getInput().getStream()) {
            byte[] var4 = IO.readInputStream(var3);
            this.gO = "# Failed to parse as XML:\n\n" + Strings.decodeUTF8(var4);
            var2 = true;
            this.nf = true;
         } catch (Exception var8) {
         }
      }

      byte[] var9 = XmlIdentifier.getNonWhitespaceHeader(this.getInput(), 5, true);
      this.gP = XmlIdentifier.isXMLHeader(var9);
      return var2;
   }

   private boolean q(int var1, boolean var2) {
      byte[] var3;
      try (Nt.eo var4 = new Nt.eo(this.getInput().getStream())) {
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
         this.Dw = var11.parse(var3);
         this.lm = com.pnfsoftware.jeb.corei.parsers.xml.eo.q(this.Dw);
         this.za = this.Dw.getFirstChild().getNodeName();
         return true;
      } catch (Exception var10) {
         if (var2) {
            this.q(new RuntimeException("XML parsing v2 failed", var10), Formatter.escapeBytes(var3));
         }

         return false;
      }
   }

   private boolean RF(int var1, boolean var2) {
      DocumentBuilder var3;
      try {
         DocumentBuilderFactory var4 = DocumentBuilderFactory.newInstance();
         var3 = var4.newDocumentBuilder();
      } catch (ParserConfigurationException var12) {
         xK.catching(var12);
         JebCoreService.notifySilentExceptionToClient(var12);
         return false;
      }

      try {
         try (Nt.eo var18 = new Nt.eo(this.getInput().getStream())) {
            if (var1 > 0) {
               var18.skip(var1);
            }

            this.Dw = var3.parse(var18);
            this.Dw.normalize();
            this.lm = com.pnfsoftware.jeb.corei.parsers.xml.eo.q(this.Dw);
            this.za = this.Dw.getFirstChild().getNodeName();
         }

         return true;
      } catch (IOException var16) {
         xK.catchingSilent(var16);
         return false;
      } catch (SAXException var17) {
         this.logError(true, S.L("Improper XML"));
         xK.catchingSilent(var17);
         boolean var5 = q(var17.getMessage());
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

            this.q(var17, var6);
         }

         return false;
      }
   }

   @Override
   public Document getDocument() {
      return this.q(true);
   }

   public Document q(boolean var1) {
      if (var1 && !(this.Dw instanceof XDocument)) {
         Document var2 = (Document)this.Dw.cloneNode(true);
         return var2;
      } else {
         return this.Dw;
      }
   }

   @Override
   public boolean hasXmlDeclaration() {
      return this.gP;
   }

   @Override
   public IUnitFormatter getFormatter() {
      IUnitFormatter var1 = super.getFormatter();
      if ((this.Dw != null || this.gO != null) && UnitFormatterUtil.getPresentationByIdentifier(var1, 1L) == null) {
         var1.addPresentation(new iZ(this, 1L, S.L("Formatted Text"), true), false);
      }

      return var1;
   }

   @Override
   public List getContributions() {
      List var1 = super.getContributions();
      if (this.Uv == null && this.oW == null && this.Dw != null) {
         IApkUnit var2 = (IApkUnit)UnitUtil.findAncestor(this, IApkUnit.class, false);
         if (var2 != null) {
            boolean var3 = this.Dw();
            boolean var4 = var3 ? false : this.xK();
            if (!var3 && !var4) {
               this.oW = "apk.unknown";
            } else {
               this.oW = "apk.xml";
               String var5 = var2.getPropertyManager().getString("AndroidJavadocRoot");
               if (Strings.isBlank(var5)) {
                  xK.debug("This javadoc root location is illegal: %s", var5);
                  this.oW = "apk.misconfigured";
                  return var1;
               }

               try {
                  Net var6 = ((zJ)RuntimeProjectUtil.findProject(this).getEnginesContext()).xK();
                  if (var3) {
                     this.Uv = new com.pnfsoftware.jeb.corei.parsers.dex.nI(this, var5, var6);
                     var1.add(this.Uv);
                  } else if (var4) {
                     this.Uv = com.pnfsoftware.jeb.corei.parsers.dex.ej.q(this, var5, var6);
                     if (this.Uv != null) {
                        var1.add(this.Uv);
                     }
                  }
               } catch (Exception var7) {
                  xK.error(
                     S.L("The contribution for an XML unit cannot be created.\nPlease update your properties, this javadoc root location seems illegal: %s"),
                     var5
                  );
                  xK.catching(var7);
               }
            }
         } else {
            this.oW = "unknown";
         }
      }

      return var1;
   }

   private boolean xK() {
      return this.RF("resources");
   }

   private boolean Dw() {
      return this.RF("manifest");
   }

   private boolean RF(String var1) {
      return var1 != null && var1.equals(this.za);
   }

   private Node q(long var1) {
      if (this.Dw == null) {
         return null;
      } else {
         long var3 = var1 >>> 8;
         return var3 != 0L && var3 < this.lm.size() ? (Node)this.lm.get((int)var3) : null;
      }
   }

   @Override
   public Object getItemObject(long var1) {
      if (this.Dw == null) {
         return null;
      } else {
         Node var3 = this.q(var1);
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
      Node var3 = this.q(var1);
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
         } while (var3 != this.Dw);

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

   public static boolean q(String var0) {
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

   private void q(Exception var1, String var2) {
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

   private static class eo extends FilterInputStream {
      protected eo(InputStream var1) {
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
