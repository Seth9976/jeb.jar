package com.pnfsoftware.jeb.corei.parsers.html;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.CodeDocument;
import com.pnfsoftware.jeb.core.output.text.ITextDocumentPart;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.text.StringEscapeUtils;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.nodes.XmlDeclaration;

public class eo extends CodeDocument {
   private static final ILogger Dw = GlobalLog.getLogger(eo.class);
   private static final List Uv = Arrays.asList("area", "base", "br", "col", "embed", "hr", "img", "input", "link", "meta", "param", "source", "track", "wbr");
   private static final List oW = Arrays.asList("b", "strong", "i", "em", "mark", "small", "del", "ins", "sub", "sup");
   private static final List gO = Arrays.asList("a");
   private nI nf;
   private CU gP;
   boolean q;
   boolean RF = false;
   boolean xK;

   public eo(nI var1) {
      this.nf = var1;
      this.q(var1);
   }

   @Override
   public IUnit getUnit() {
      return this.nf;
   }

   @Override
   public long getAnchorCount() {
      return 1L;
   }

   @Override
   public ITextDocumentPart getDocumentPart(long var1, int var3, int var4) {
      return this.gP;
   }

   private void q(nI var1) {
      this.gP = new CU(0L);
      this.gP.setIndentationString("  ");
      Document var2 = var1.q();
      this.gP.registerAnchor("wholeDocumentAnchor");
      this.q((Node)var2);
      this.gP.eol();
   }

   void q(Node var1) {
      if (var1 instanceof Document) {
         this.q((Document)var1);
      } else if (var1 instanceof Element) {
         this.q((Element)var1);
      } else if (var1 instanceof TextNode) {
         this.q((TextNode)var1);
      } else if (var1 instanceof DataNode) {
         this.q((DataNode)var1);
      } else if (var1 instanceof Comment) {
         this.q((Comment)var1);
      } else if (var1 instanceof XmlDeclaration) {
         this.q((XmlDeclaration)var1);
      }
   }

   void q(Document var1) {
      for (Node var3 : var1.childNodes()) {
         this.q(var3);
      }
   }

   void q(Element var1) {
      if (!this.gP.isCurrentLineEmpty() && !this.xK) {
         this.gP.eol();
      }

      String var2 = var1.tagName();
      this.gP.append("<");
      this.gP.appendAndRecord(var2, ItemClassIdentifiers.MARKUP_ELEMENT);

      for (Attribute var4 : var1.attributes()) {
         String var5 = var4.getKey();
         String var6 = var4.getValue();
         this.gP.space();
         this.gP.appendAndRecord(var5, ItemClassIdentifiers.MARKUP_ATTRIBUTE_NAME);
         this.gP.append("=\"");
         this.gP.appendAndRecord(var6, ItemClassIdentifiers.MARKUP_ATTRIBUTE_VALUE);
         this.gP.append("\"");
      }

      List var9 = var1.childNodes();
      if (var9.isEmpty()) {
         if (this.RF(var2)) {
            this.gP.append("/>");
         } else {
            this.gP.append("></");
            this.gP.appendAndRecord(var2, ItemClassIdentifiers.MARKUP_ELEMENT);
            this.gP.append(">");
         }
      } else {
         boolean var10 = var9.size() >= 2;
         boolean var11 = this.xK;
         boolean var12 = this.q;
         this.xK = this.q(var9);
         this.q = "script".equals(var2);
         this.gP.append(">");
         if (Strings.isContainedIn(var2, "pre")) {
            String var13 = var1.outerHtml();
            this.gP.eol();
            int var14 = this.gP.getIndentationLevel();
            this.gP.setIndentationLevel(0);
            this.gP.appendMulti(var13.substring(var13.indexOf(62) + 1, var13.lastIndexOf(60)));
            this.gP.setIndentationLevel(var14);
         } else {
            this.q(var10 && !this.xK);
            this.gP.incrementIndentationLevel();

            for (Node var8 : var9) {
               this.q(var8);
            }

            this.gP.decrementIndentationLevel();
            this.q(var10 && !this.xK);
         }

         this.gP.append("</");
         this.gP.appendAndRecord(var2, ItemClassIdentifiers.MARKUP_ELEMENT);
         this.gP.append(">");
         this.xK = var11;
         this.q = var12;
      }

      this.RF(var1);
   }

   boolean q(List var1) {
      if (var1.size() == 0) {
         return false;
      } else {
         if (var1.size() == 1) {
            if (var1.get(0) instanceof TextNode) {
               return true;
            }

            if (var1.get(0) instanceof Element) {
               String var2 = ((Element)var1.get(0)).tagName();
               if (gO.contains(var2) && this.q(((Node)var1.get(0)).childNodes())) {
                  return true;
               }
            }
         }

         Iterator var5 = var1.iterator();

         while (true) {
            if (!var5.hasNext()) {
               return true;
            }

            Node var3 = (Node)var5.next();
            if (!(var3 instanceof TextNode)) {
               if (!(var3 instanceof Element)) {
                  break;
               }

               String var4 = ((Element)var3).tagName();
               if (!oW.contains(var4) || !this.q(var3.childNodes())) {
                  break;
               }
            }
         }

         return false;
      }
   }

   void RF(Node var1) {
      Node var2 = var1.nextSibling();
      if (var2 instanceof TextNode && !((TextNode)var2).getWholeText().contains("\n")) {
         var2 = var2.nextSibling();
      }

      if (!(var2 instanceof Comment) && !this.xK) {
         this.gP.eol();
      }
   }

   void q(boolean var1) {
      if (var1 && !this.gP.isCurrentLineEmpty()) {
         this.gP.eol();
      }
   }

   void q() {
      this.q(true);
   }

   void q(Comment var1) {
      Assert.a(var1.childNodes().isEmpty());
      String var2 = var1.getData();
      this.gP.append("<!--");
      if (this.RF) {
         this.gP.appendAndRecord(Strings.replaceNewLines(var2, " "), ItemClassIdentifiers.MARKUP_COMMENT);
      } else {
         int var3 = 0;
         int var4 = -1;
         int var5 = this.gP.getCurrentMarginLength() + this.gP.getIndentationString().length();

         for (String var9 : Strings.splitLines(var1.getData())) {
            if (var3 >= 1) {
               this.gP.eol();
               if (var9.trim().startsWith("<")) {
                  if (var4 == -1) {
                     var4 = this.q(var9);
                  }

                  int var10 = this.q(var9);
                  if (var10 >= var4 && var10 >= var5) {
                     var9 = var9.substring(var5);
                  } else {
                     var9 = var9.trim();
                  }
               }
            }

            this.gP.appendAndRecord(var9, ItemClassIdentifiers.MARKUP_COMMENT);
            var3++;
         }
      }

      this.gP.append("-->");
      this.q();
   }

   void q(TextNode var1) {
      Assert.a(var1.childNodes().isEmpty(), "HTML Text node should not have attributes");
      if (!var1.isBlank()) {
         String var2 = var1.getWholeText();
         var2 = Strings.replaceNewLines(var2, " ");
         var2 = StringEscapeUtils.escapeHtml4(var2);
         var2 = StringUtil.normaliseWhitespace(var2);
         this.gP.appendAndRecord(var2, ItemClassIdentifiers.MARKUP_TEXT);
         if (!this.xK) {
            this.q();
         }
      }
   }

   void q(DataNode var1) {
      int var2 = 0;
      boolean var3 = false;
      int var4 = -1;
      if (this.q) {
         this.gP.eol();
      }

      boolean var5 = true;

      for (String var9 : Strings.splitLines(var1.getWholeData())) {
         boolean var10 = var5;
         if (var9.endsWith("\\")) {
            var5 = false;
         } else {
            var5 = true;
         }

         if (!var10) {
            if (var3) {
               this.gP.eol();
            } else {
               var3 = true;
            }

            int var11 = this.gP.getIndentationLevel();
            this.gP.setIndentationLevel(0);
            this.gP.appendAndRecord(var9, ItemClassIdentifiers.MARKUP_ACTIVE);
            this.gP.setIndentationLevel(var11);
         } else {
            if (this.q) {
               if (Strings.isBlank(var9)) {
                  var2++;
                  continue;
               }

               if (var2 > 0) {
                  if (var4 == -1) {
                     var4 = this.q(var9);
                     if (var4 == -1) {
                        var2++;
                        continue;
                     }
                  }

                  int var12 = this.q(var9);
                  if (var12 > var4) {
                     var9 = var9.substring(var4);
                  } else {
                     var9 = var9.trim();
                  }
               } else {
                  var9 = var9.trim();
               }
            }

            if (var3) {
               this.gP.eol();
            } else {
               var3 = true;
            }

            this.gP.appendAndRecord(var9, ItemClassIdentifiers.MARKUP_ACTIVE);
            var2++;
         }
      }

      this.q();
   }

   private int q(String var1) {
      for (int var2 = 0; var2 < var1.length(); var2++) {
         if (var1.charAt(var2) != ' ') {
            return var2;
         }
      }

      return -1;
   }

   void q(XmlDeclaration var1) {
      boolean var2 = var1.toString().trim().startsWith("<!");
      this.gP.append("<");
      this.gP.append(var2 ? "!" : "?");
      this.gP.appendAndRecord(var1.getWholeDeclaration(), ItemClassIdentifiers.MARKUP_OTHER);
      this.gP.append(var2 ? "!" : "?");
      this.gP.append(">");
      this.q();
   }

   private boolean RF(String var1) {
      return Uv.contains(var1.toLowerCase());
   }
}
