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

public class Av extends CodeDocument {
   private static final ILogger wS = GlobalLog.getLogger(Av.class);
   private static final List UT = Arrays.asList("area", "base", "br", "col", "embed", "hr", "img", "input", "link", "meta", "param", "source", "track", "wbr");
   private static final List E = Arrays.asList("b", "strong", "i", "em", "mark", "small", "del", "ins", "sub", "sup");
   private static final List sY = Arrays.asList("a");
   private K ys;
   private Sv ld;
   boolean pC;
   boolean A = false;
   boolean kS;

   public Av(K var1) {
      this.ys = var1;
      this.pC(var1);
   }

   @Override
   public IUnit getUnit() {
      return this.ys;
   }

   @Override
   public long getAnchorCount() {
      return 1L;
   }

   @Override
   public ITextDocumentPart getDocumentPart(long var1, int var3, int var4) {
      return this.ld;
   }

   private void pC(K var1) {
      this.ld = new Sv(0L);
      this.ld.setIndentationString("  ");
      Document var2 = var1.pC();
      this.ld.registerAnchor("wholeDocumentAnchor");
      this.pC((Node)var2);
      this.ld.eol();
   }

   void pC(Node var1) {
      if (var1 instanceof Document) {
         this.pC((Document)var1);
      } else if (var1 instanceof Element) {
         this.pC((Element)var1);
      } else if (var1 instanceof TextNode) {
         this.pC((TextNode)var1);
      } else if (var1 instanceof DataNode) {
         this.pC((DataNode)var1);
      } else if (var1 instanceof Comment) {
         this.pC((Comment)var1);
      } else if (var1 instanceof XmlDeclaration) {
         this.pC((XmlDeclaration)var1);
      }
   }

   void pC(Document var1) {
      for (Node var3 : var1.childNodes()) {
         this.pC(var3);
      }
   }

   void pC(Element var1) {
      if (!this.ld.isCurrentLineEmpty() && !this.kS) {
         this.ld.eol();
      }

      String var2 = var1.tagName();
      this.ld.append("<");
      this.ld.appendAndRecord(var2, ItemClassIdentifiers.MARKUP_ELEMENT);

      for (Attribute var4 : var1.attributes()) {
         String var5 = var4.getKey();
         String var6 = var4.getValue();
         this.ld.space();
         this.ld.appendAndRecord(var5, ItemClassIdentifiers.MARKUP_ATTRIBUTE_NAME);
         this.ld.append("=\"");
         this.ld.appendAndRecord(var6, ItemClassIdentifiers.MARKUP_ATTRIBUTE_VALUE);
         this.ld.append("\"");
      }

      List var9 = var1.childNodes();
      if (var9.isEmpty()) {
         if (this.A(var2)) {
            this.ld.append("/>");
         } else {
            this.ld.append("></");
            this.ld.appendAndRecord(var2, ItemClassIdentifiers.MARKUP_ELEMENT);
            this.ld.append(">");
         }
      } else {
         boolean var10 = var9.size() >= 2;
         boolean var11 = this.kS;
         boolean var12 = this.pC;
         this.kS = this.pC(var9);
         this.pC = "script".equals(var2);
         this.ld.append(">");
         if (Strings.isContainedIn(var2, "pre")) {
            String var13 = var1.outerHtml();
            this.ld.eol();
            int var14 = this.ld.getIndentationLevel();
            this.ld.setIndentationLevel(0);
            this.ld.appendMulti(var13.substring(var13.indexOf(62) + 1, var13.lastIndexOf(60)));
            this.ld.setIndentationLevel(var14);
         } else {
            this.pC(var10 && !this.kS);
            this.ld.incrementIndentationLevel();

            for (Node var8 : var9) {
               this.pC(var8);
            }

            this.ld.decrementIndentationLevel();
            this.pC(var10 && !this.kS);
         }

         this.ld.append("</");
         this.ld.appendAndRecord(var2, ItemClassIdentifiers.MARKUP_ELEMENT);
         this.ld.append(">");
         this.kS = var11;
         this.pC = var12;
      }

      this.A(var1);
   }

   boolean pC(List var1) {
      if (var1.size() == 0) {
         return false;
      } else {
         if (var1.size() == 1) {
            if (var1.get(0) instanceof TextNode) {
               return true;
            }

            if (var1.get(0) instanceof Element) {
               String var2 = ((Element)var1.get(0)).tagName();
               if (sY.contains(var2) && this.pC(((Node)var1.get(0)).childNodes())) {
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
               if (!E.contains(var4) || !this.pC(var3.childNodes())) {
                  break;
               }
            }
         }

         return false;
      }
   }

   void A(Node var1) {
      Node var2 = var1.nextSibling();
      if (var2 instanceof TextNode && !((TextNode)var2).getWholeText().contains("\n")) {
         var2 = var2.nextSibling();
      }

      if (!(var2 instanceof Comment) && !this.kS) {
         this.ld.eol();
      }
   }

   void pC(boolean var1) {
      if (var1 && !this.ld.isCurrentLineEmpty()) {
         this.ld.eol();
      }
   }

   void pC() {
      this.pC(true);
   }

   void pC(Comment var1) {
      Assert.a(var1.childNodes().isEmpty());
      String var2 = var1.getData();
      this.ld.append("<!--");
      if (this.A) {
         this.ld.appendAndRecord(Strings.replaceNewLines(var2, " "), ItemClassIdentifiers.MARKUP_COMMENT);
      } else {
         int var3 = 0;
         int var4 = -1;
         int var5 = this.ld.getCurrentMarginLength() + this.ld.getIndentationString().length();

         for (String var9 : Strings.splitLines(var1.getData())) {
            if (var3 >= 1) {
               this.ld.eol();
               if (var9.trim().startsWith("<")) {
                  if (var4 == -1) {
                     var4 = this.pC(var9);
                  }

                  int var10 = this.pC(var9);
                  if (var10 >= var4 && var10 >= var5) {
                     var9 = var9.substring(var5);
                  } else {
                     var9 = var9.trim();
                  }
               }
            }

            this.ld.appendAndRecord(var9, ItemClassIdentifiers.MARKUP_COMMENT);
            var3++;
         }
      }

      this.ld.append("-->");
      this.pC();
   }

   void pC(TextNode var1) {
      Assert.a(var1.childNodes().isEmpty(), "HTML Text node should not have attributes");
      if (!var1.isBlank()) {
         String var2 = var1.getWholeText();
         var2 = Strings.replaceNewLines(var2, " ");
         var2 = StringEscapeUtils.escapeHtml4(var2);
         var2 = StringUtil.normaliseWhitespace(var2);
         this.ld.appendAndRecord(var2, ItemClassIdentifiers.MARKUP_TEXT);
         if (!this.kS) {
            this.pC();
         }
      }
   }

   void pC(DataNode var1) {
      int var2 = 0;
      boolean var3 = false;
      int var4 = -1;
      if (this.pC) {
         this.ld.eol();
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
               this.ld.eol();
            } else {
               var3 = true;
            }

            int var11 = this.ld.getIndentationLevel();
            this.ld.setIndentationLevel(0);
            this.ld.appendAndRecord(var9, ItemClassIdentifiers.MARKUP_ACTIVE);
            this.ld.setIndentationLevel(var11);
         } else {
            if (this.pC) {
               if (Strings.isBlank(var9)) {
                  var2++;
                  continue;
               }

               if (var2 > 0) {
                  if (var4 == -1) {
                     var4 = this.pC(var9);
                     if (var4 == -1) {
                        var2++;
                        continue;
                     }
                  }

                  int var12 = this.pC(var9);
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
               this.ld.eol();
            } else {
               var3 = true;
            }

            this.ld.appendAndRecord(var9, ItemClassIdentifiers.MARKUP_ACTIVE);
            var2++;
         }
      }

      this.pC();
   }

   private int pC(String var1) {
      for (int var2 = 0; var2 < var1.length(); var2++) {
         if (var1.charAt(var2) != ' ') {
            return var2;
         }
      }

      return -1;
   }

   void pC(XmlDeclaration var1) {
      boolean var2 = var1.toString().trim().startsWith("<!");
      this.ld.append("<");
      this.ld.append(var2 ? "!" : "?");
      this.ld.appendAndRecord(var1.getWholeDeclaration(), ItemClassIdentifiers.MARKUP_OTHER);
      this.ld.append(var2 ? "!" : "?");
      this.ld.append(">");
      this.pC();
   }

   private boolean A(String var1) {
      return UT.contains(var1.toLowerCase());
   }
}
