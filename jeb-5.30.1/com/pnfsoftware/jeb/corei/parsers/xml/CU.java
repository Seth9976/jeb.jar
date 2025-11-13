package com.pnfsoftware.jeb.corei.parsers.xml;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.output.AddressConversionPrecision;
import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.AssemblyItem;
import com.pnfsoftware.jeb.core.output.code.CodeDocument;
import com.pnfsoftware.jeb.core.output.code.CodeLine;
import com.pnfsoftware.jeb.core.output.text.ICoordinates;
import com.pnfsoftware.jeb.core.output.text.ITextDocumentPart;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionGroup;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.properties.IPropertyManager;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeBoolean;
import com.pnfsoftware.jeb.core.properties.impl.PropertyTypeInteger;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IXmlUnit;
import com.pnfsoftware.jeb.util.events.IEventListener;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class CU extends CodeDocument {
   private static final ILogger xK = GlobalLog.getLogger(CU.class);
   public static final String q = "AttributesOnSeparateLines";
   public static final String RF = "IndentSpaceCount";
   private IXmlUnit Dw;
   private IPropertyManager Uv;
   private IEventListener oW;
   private IEventListener gO;
   private boolean nf;
   private oM gP;
   private int za;
   private boolean lm;

   public static void q(IPropertyDefinitionManager var0) {
      IPropertyDefinitionGroup var1 = var0.addGroup("text");
      var1.addDefinition("IndentSpaceCount", PropertyTypeInteger.create(0, 8, 2), S.L("The space count of the indentation string for sub-elements (in [0, 8])"));
      var1.addDefinition(
         "AttributesOnSeparateLines",
         PropertyTypeBoolean.create(true),
         S.L("For elements having multiple attributes, each key-value pair will be rendered on a separate line")
      );
   }

   public CU(IXmlUnit var1) {
      this.Dw = var1;
      this.q(false);
      var1.addListener(this.oW = new nI(this));
      this.Uv = var1.getPropertyManager();
      this.Uv.addListener(this.gO = new ej(this));
   }

   @Override
   public void dispose() {
      if (!this.nf) {
         super.dispose();
         this.Dw.removeListener(this.oW);
         this.Uv.removeListener(this.gO);
         this.nf = true;
      }
   }

   private void q(boolean var1) {
      IPropertyManager var2 = this.Dw.getPropertyManager();
      this.za = var2.getInteger("IndentSpaceCount");
      this.lm = var2.getBoolean("AttributesOnSeparateLines");
      if (var1) {
         this.notifyListeners(new JebEvent(J.UnitChange));
      }
   }

   @Override
   public IUnit getUnit() {
      return this.Dw;
   }

   @Override
   public long getAnchorCount() {
      return 1L;
   }

   @Override
   public String coordinatesToAddress(ICoordinates var1, AddressConversionPrecision var2) {
      if (var1 == null) {
         return null;
      } else {
         int var3 = var1.getLineDelta();
         if (this.gP != null && var3 >= 0 && var3 < this.gP.getCountOfLines()) {
            CodeLine var4 = this.gP.getLine(var3);

            for (AssemblyItem var7 : var4.getItems()) {
               if (var7.getOffset() <= var1.getColumnOffset() && var1.getColumnOffset() < var7.getOffsetEnd()) {
                  return this.Dw.getAddressOfItem(var7.getItemId());
               }
            }

            return super.coordinatesToAddress(var1, var2);
         } else {
            return null;
         }
      }
   }

   @Override
   public ITextDocumentPart getDocumentPart(long var1, int var3, int var4) {
      this.gP = new oM(0L);
      String var5 = Strings.spaces(Math.min(8, Math.max(0, this.za)));
      this.gP.setIndentationString(var5);
      this.gP.registerAnchor("wholeDocumentAnchor");
      Document var6 = ((Nt)this.Dw).q(false);
      if (var6 != null) {
         synchronized (var6) {
            this.q((Node)var6);
         }
      }

      this.gP.eol();
      return this.gP;
   }

   private void q(Node var1) {
      if (!this.gP.isCurrentLineEmpty()) {
         this.gP.eol();
      }

      if (var1 instanceof Document) {
         this.q((Document)var1);
      } else if (var1 instanceof Element) {
         this.q((Element)var1);
      } else if (var1 instanceof CharacterData) {
         this.q((CharacterData)var1);
      }
   }

   private void q(Document var1) {
      if (this.Dw.hasXmlDeclaration()) {
         this.gP.append("<?xml");
         this.gP.space();
         String var2 = var1.getXmlVersion();
         if (var2 != null) {
            this.gP.appendAndRecord("version", ItemClassIdentifiers.MARKUP_ATTRIBUTE_NAME);
            this.gP.append("=\"");
            this.gP.appendAndRecord(var2, ItemClassIdentifiers.MARKUP_ATTRIBUTE_VALUE);
            this.gP.append("\"");
            this.gP.space();
         }

         String var3 = var1.getXmlEncoding();
         if (var3 != null) {
            this.gP.appendAndRecord("encoding", ItemClassIdentifiers.MARKUP_ATTRIBUTE_NAME);
            this.gP.append("=\"");
            this.gP.appendAndRecord(var3, ItemClassIdentifiers.MARKUP_ATTRIBUTE_VALUE);
            this.gP.append("\"");
         }

         boolean var4 = var1.getXmlStandalone();
         if (var4) {
            this.gP.appendAndRecord("standalone", ItemClassIdentifiers.MARKUP_ATTRIBUTE_NAME);
            this.gP.append("=\"");
            this.gP.appendAndRecord("yes", ItemClassIdentifiers.MARKUP_ATTRIBUTE_VALUE);
            this.gP.append("\"");
         }

         this.gP.append("?>");
      }

      NodeList var5 = var1.getChildNodes();

      for (int var6 = 0; var6 < var5.getLength(); var6++) {
         Node var7 = var5.item(var6);
         this.q(var7);
      }
   }

   private long q(long var1, int var3, boolean var4) {
      return var3 >= 128 ? 0L : var1 | var3 << 1 | (var4 ? 0 : 1);
   }

   private long q(long var1) {
      return var1 | 192L;
   }

   private long RF(long var1) {
      return var1 | 193L;
   }

   private long q(long var1, int var3) {
      return var3 >= 64 ? 0L : var1 | 128L | var3;
   }

   private long RF(Node var1) {
      long var2 = 0L;
      Object var4 = var1.getUserData("itemId");
      if (var4 != null) {
         var2 = (long)((Integer)var4).intValue() << 8;
      }

      return var2;
   }

   private void q(Element var1) {
      long var2 = this.RF(var1);
      String var4 = var1.getTagName();
      this.gP.append("<");
      this.gP.appendAndRecord(var4, ItemClassIdentifiers.MARKUP_ELEMENT, this.q(var2));
      this.gP.incrementIndentationLevel();
      NamedNodeMap var5 = var1.getAttributes();
      int var6 = var5.getLength();

      for (int var7 = 0; var7 < var6; var7++) {
         Attr var8 = (Attr)var5.item(var7);
         String var9 = var8.getName();
         String var10 = var8.getValue();
         if (this.lm && var6 != 1) {
            this.gP.eol();
         } else {
            this.gP.space();
         }

         this.gP.appendAndRecord(var9, ItemClassIdentifiers.MARKUP_ATTRIBUTE_NAME, this.q(var2, var7, true));
         this.gP.append("=\"");
         this.gP.appendAndRecord(var10, ItemClassIdentifiers.MARKUP_ATTRIBUTE_VALUE, this.q(var2, var7, false));
         this.gP.append("\"");
      }

      this.gP.decrementIndentationLevel();
      NodeList var11 = var1.getChildNodes();
      if (var11.getLength() == 0) {
         this.gP.append("/>");
      } else {
         this.gP.append(">");
         if (var11.getLength() == 1 && var11.item(0) instanceof CharacterData) {
            this.q((CharacterData)var11.item(0));
         } else {
            this.gP.incrementIndentationLevel();

            for (int var12 = 0; var12 < var11.getLength(); var12++) {
               Node var13 = var11.item(var12);
               this.q(var13);
            }

            this.gP.decrementIndentationLevel();
            if (!this.gP.isCurrentLineEmpty()) {
               this.gP.eol();
            }
         }

         this.gP.append("</");
         this.gP.appendAndRecord(var4, ItemClassIdentifiers.MARKUP_ELEMENT, this.RF(var2));
         this.gP.append(">");
      }
   }

   private void q(CharacterData var1) {
      String var2 = var1.getTextContent();
      if (!var2.isEmpty()) {
         boolean var3 = false;
         if (var1 instanceof Comment) {
            this.gP.append("<!--");
         } else if (var1 instanceof CDATASection) {
            this.gP.append("<![CDATA[");
         } else {
            if (!(var1 instanceof Text)) {
               throw new RuntimeException();
            }

            var2 = this.q(var2);
            var3 = true;
         }

         long var4 = this.RF(var1);
         if (var3) {
            String[] var6 = var2.split("( |\n)", -1);
            int var7 = 0;

            for (String var11 : var6) {
               if (!var11.isEmpty()) {
                  this.gP.appendAndRecord(var11, ItemClassIdentifiers.MARKUP_TEXT, this.q(var4, var7));
                  if (++var7 < var6.length) {
                     this.gP.space();
                  }
               }
            }
         } else {
            int var12 = this.gP.getIndentationLevel();
            this.gP.indentReset();
            String[] var13 = Strings.splitLines(var2);

            for (int var14 = 0; var14 < var13.length; var14++) {
               String var15 = var13[var14];
               if (var15.isEmpty()) {
                  this.gP.eol();
               } else {
                  this.gP.appendAndRecord(var15, ItemClassIdentifiers.MARKUP_TEXT, this.q(var4, var14));
                  if (var14 + 1 < var13.length) {
                     this.gP.eol();
                  }
               }
            }

            this.gP.setIndentationLevel(var12);
         }

         if (var1 instanceof Comment) {
            this.gP.append("-->");
         } else if (var1 instanceof CDATASection) {
            this.gP.append("]]>");
         }
      }
   }

   private String q(String var1) {
      int var2;
      for (var2 = 0; var2 < var1.length(); var2++) {
         char var3 = var1.charAt(var2);
         if (!Character.isWhitespace(var3)) {
            break;
         }
      }

      int var5;
      for (var5 = var1.length() - 1; var5 > var2; var5--) {
         char var4 = var1.charAt(var5);
         if (!Character.isWhitespace(var4)) {
            break;
         }
      }

      return var1.substring(var2, var5 + 1);
   }
}
