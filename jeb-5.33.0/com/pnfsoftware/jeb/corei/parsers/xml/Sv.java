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

public class Sv extends CodeDocument {
   private static final ILogger pC = GlobalLog.getLogger(Sv.class);
   private IXmlUnit A;
   private IPropertyManager kS;
   private IEventListener wS;
   private IEventListener UT;
   private boolean E;
   private bO sY;
   private int ys;
   private boolean ld;

   public static void pC(IPropertyDefinitionManager var0) {
      IPropertyDefinitionGroup var1 = var0.addGroup("text");
      var1.addDefinition("IndentSpaceCount", PropertyTypeInteger.create(0, 8, 2), S.L("The space count of the indentation string for sub-elements (in [0, 8])"));
      var1.addDefinition(
         "AttributesOnSeparateLines",
         PropertyTypeBoolean.create(true),
         S.L("For elements having multiple attributes, each key-value pair will be rendered on a separate line")
      );
   }

   public Sv(IXmlUnit var1) {
      this.A = var1;
      this.pC(false);
      var1.addListener(this.wS = new K(this));
      this.kS = var1.getPropertyManager();
      this.kS.addListener(this.UT = new Ws(this));
   }

   @Override
   public void dispose() {
      if (!this.E) {
         super.dispose();
         this.A.removeListener(this.wS);
         this.kS.removeListener(this.UT);
         this.E = true;
      }
   }

   private void pC(boolean var1) {
      IPropertyManager var2 = this.A.getPropertyManager();
      this.ys = var2.getInteger("IndentSpaceCount");
      this.ld = var2.getBoolean("AttributesOnSeparateLines");
      if (var1) {
         this.notifyListeners(new JebEvent(J.UnitChange));
      }
   }

   @Override
   public IUnit getUnit() {
      return this.A;
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
         if (this.sY != null && var3 >= 0 && var3 < this.sY.getCountOfLines()) {
            CodeLine var4 = this.sY.getLine(var3);

            for (AssemblyItem var7 : var4.getItems()) {
               if (var7.getOffset() <= var1.getColumnOffset() && var1.getColumnOffset() < var7.getOffsetEnd()) {
                  return this.A.getAddressOfItem(var7.getItemId());
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
      this.sY = new bO(0L);
      String var5 = Strings.spaces(Math.min(8, Math.max(0, this.ys)));
      this.sY.setIndentationString(var5);
      this.sY.registerAnchor("wholeDocumentAnchor");
      Document var6 = ((cq)this.A).pC(false);
      if (var6 != null) {
         synchronized (var6) {
            this.pC((Node)var6);
         }
      }

      this.sY.eol();
      return this.sY;
   }

   private void pC(Node var1) {
      if (!this.sY.isCurrentLineEmpty()) {
         this.sY.eol();
      }

      if (var1 instanceof Document) {
         this.pC((Document)var1);
      } else if (var1 instanceof Element) {
         this.pC((Element)var1);
      } else if (var1 instanceof CharacterData) {
         this.pC((CharacterData)var1);
      }
   }

   private void pC(Document var1) {
      if (this.A.hasXmlDeclaration()) {
         this.sY.append("<?xml");
         this.sY.space();
         String var2 = var1.getXmlVersion();
         if (var2 != null) {
            this.sY.appendAndRecord("version", ItemClassIdentifiers.MARKUP_ATTRIBUTE_NAME);
            this.sY.append("=\"");
            this.sY.appendAndRecord(var2, ItemClassIdentifiers.MARKUP_ATTRIBUTE_VALUE);
            this.sY.append("\"");
            this.sY.space();
         }

         String var3 = var1.getXmlEncoding();
         if (var3 != null) {
            this.sY.appendAndRecord("encoding", ItemClassIdentifiers.MARKUP_ATTRIBUTE_NAME);
            this.sY.append("=\"");
            this.sY.appendAndRecord(var3, ItemClassIdentifiers.MARKUP_ATTRIBUTE_VALUE);
            this.sY.append("\"");
         }

         boolean var4 = var1.getXmlStandalone();
         if (var4) {
            this.sY.appendAndRecord("standalone", ItemClassIdentifiers.MARKUP_ATTRIBUTE_NAME);
            this.sY.append("=\"");
            this.sY.appendAndRecord("yes", ItemClassIdentifiers.MARKUP_ATTRIBUTE_VALUE);
            this.sY.append("\"");
         }

         this.sY.append("?>");
      }

      NodeList var5 = var1.getChildNodes();

      for (int var6 = 0; var6 < var5.getLength(); var6++) {
         Node var7 = var5.item(var6);
         this.pC(var7);
      }
   }

   private long pC(long var1, int var3, boolean var4) {
      return var3 >= 128 ? 0L : var1 | var3 << 1 | (var4 ? 0 : 1);
   }

   private long pC(long var1) {
      return var1 | 192L;
   }

   private long A(long var1) {
      return var1 | 193L;
   }

   private long pC(long var1, int var3) {
      return var3 >= 64 ? 0L : var1 | 128L | var3;
   }

   private long A(Node var1) {
      long var2 = 0L;
      Object var4 = var1.getUserData("itemId");
      if (var4 != null) {
         var2 = (long)((Integer)var4).intValue() << 8;
      }

      return var2;
   }

   private void pC(Element var1) {
      long var2 = this.A(var1);
      String var4 = var1.getTagName();
      this.sY.append("<");
      this.sY.appendAndRecord(var4, ItemClassIdentifiers.MARKUP_ELEMENT, this.pC(var2));
      this.sY.incrementIndentationLevel();
      NamedNodeMap var5 = var1.getAttributes();
      int var6 = var5.getLength();

      for (int var7 = 0; var7 < var6; var7++) {
         Attr var8 = (Attr)var5.item(var7);
         String var9 = var8.getName();
         String var10 = var8.getValue();
         if (this.ld && var6 != 1) {
            this.sY.eol();
         } else {
            this.sY.space();
         }

         this.sY.appendAndRecord(var9, ItemClassIdentifiers.MARKUP_ATTRIBUTE_NAME, this.pC(var2, var7, true));
         this.sY.append("=\"");
         this.sY.appendAndRecord(var10, ItemClassIdentifiers.MARKUP_ATTRIBUTE_VALUE, this.pC(var2, var7, false));
         this.sY.append("\"");
      }

      this.sY.decrementIndentationLevel();
      NodeList var11 = var1.getChildNodes();
      if (var11.getLength() == 0) {
         this.sY.append("/>");
      } else {
         this.sY.append(">");
         if (var11.getLength() == 1 && var11.item(0) instanceof CharacterData) {
            this.pC((CharacterData)var11.item(0));
         } else {
            this.sY.incrementIndentationLevel();

            for (int var12 = 0; var12 < var11.getLength(); var12++) {
               Node var13 = var11.item(var12);
               this.pC(var13);
            }

            this.sY.decrementIndentationLevel();
            if (!this.sY.isCurrentLineEmpty()) {
               this.sY.eol();
            }
         }

         this.sY.append("</");
         this.sY.appendAndRecord(var4, ItemClassIdentifiers.MARKUP_ELEMENT, this.A(var2));
         this.sY.append(">");
      }
   }

   private void pC(CharacterData var1) {
      String var2 = var1.getTextContent();
      if (!var2.isEmpty()) {
         boolean var3 = false;
         if (var1 instanceof Comment) {
            this.sY.append("<!--");
         } else if (var1 instanceof CDATASection) {
            this.sY.append("<![CDATA[");
         } else {
            if (!(var1 instanceof Text)) {
               throw new RuntimeException();
            }

            var2 = this.pC(var2);
            var3 = true;
         }

         long var4 = this.A(var1);
         if (var3) {
            String[] var6 = var2.split("( |\n)", -1);
            int var7 = 0;

            for (String var11 : var6) {
               if (!var11.isEmpty()) {
                  this.sY.appendAndRecord(var11, ItemClassIdentifiers.MARKUP_TEXT, this.pC(var4, var7));
                  if (++var7 < var6.length) {
                     this.sY.space();
                  }
               }
            }
         } else {
            int var12 = this.sY.getIndentationLevel();
            this.sY.indentReset();
            String[] var13 = Strings.splitLines(var2);

            for (int var14 = 0; var14 < var13.length; var14++) {
               String var15 = var13[var14];
               if (var15.isEmpty()) {
                  this.sY.eol();
               } else {
                  this.sY.appendAndRecord(var15, ItemClassIdentifiers.MARKUP_TEXT, this.pC(var4, var14));
                  if (var14 + 1 < var13.length) {
                     this.sY.eol();
                  }
               }
            }

            this.sY.setIndentationLevel(var12);
         }

         if (var1 instanceof Comment) {
            this.sY.append("-->");
         } else if (var1 instanceof CDATASection) {
            this.sY.append("]]>");
         }
      }
   }

   private String pC(String var1) {
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
