package com.pnfsoftware.jeb.core.output.text;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.CharSequenceList;
import com.pnfsoftware.jeb.util.format.CharSequences;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TextPartUtil {
   public static void verifyPart(ITextDocumentPart var0) {
      List var1 = var0.getAnchors();
      int var2 = var1.size();
      List var3 = var0.getLines();
      int var4 = var3.size();
      if (var2 == 0) {
         throw new RuntimeException("Part without anchor");
      } else {
         IAnchor var5 = (IAnchor)var1.get(0);
         if (var5.getLineIndex() != 0) {
            throw new RuntimeException("The first anchor in the part does not reference first line in the part: " + var5.getLineIndex());
         } else {
            for (int var6 = 1; var6 < var2; var6++) {
               IAnchor var7 = (IAnchor)var1.get(var6);
               if (var7.getIdentifier() <= var5.getIdentifier() || var7.getLineIndex() <= var5.getLineIndex()) {
                  throw new RuntimeException(
                     Strings.ff(
                        "Non strictly ascending anchors: (%d,%d) vs (%d,%d) ",
                        var5.getIdentifier(),
                        var5.getLineIndex(),
                        var7.getIdentifier(),
                        var7.getLineIndex()
                     )
                  );
               }

               if (var7.getLineIndex() > var4) {
                  throw new RuntimeException(Strings.ff("Invalid line index: %d vs %d", var4, var7.getLineIndex()));
               }

               var5 = var7;
            }

            for (int var15 = 0; var15 < var4; var15++) {
               ILine var16 = var0.getLine(var15);
               CharSequence var8 = var16.getText();
               if (CharSequences.indexOf2(var8, '\r', '\n') >= 0) {
                  throw new RuntimeException(String.format("Lines within a document part must not contain new-line characters @[%d]", var15));
               }

               int var9 = 0;

               for (ITextItem var11 : var16.getItems()) {
                  int var12 = var11.getOffset();
                  if (var12 < var9) {
                     throw new RuntimeException(Strings.ff("Items in a document part must not overlap or be out-of-bound @[%d , %d]", var15, var12));
                  }

                  int var13 = var11.getLength();
                  int var14 = var12 + var13;
                  if (var14 > var8.length()) {
                     throw new RuntimeException(String.format("Items in a document part must not be out-of-bound @[%d , %d]", var15, var12));
                  }

                  var9 = var14;
               }
            }
         }
      }
   }

   public static IAnchor getFirstAnchor(ITextDocumentPart var0) {
      if (var0 == null) {
         return null;
      } else {
         int var1 = var0.getCountOfAnchors();
         return var1 == 0 ? null : var0.getAnchor(0);
      }
   }

   public static long getFirstAnchorId(ITextDocumentPart var0) {
      int var1 = var0.getCountOfAnchors();
      return var1 == 0 ? -1L : var0.getAnchor(0).getIdentifier();
   }

   public static IAnchor getLastAnchor(ITextDocumentPart var0) {
      if (var0 == null) {
         return null;
      } else {
         int var1 = var0.getCountOfAnchors();
         if (var1 == 0) {
            return null;
         } else {
            IAnchor var2 = var0.getAnchor(var1 - 1);
            if (var2.getLineIndex() == var0.getCountOfLines()) {
               if (var1 == 1) {
                  return null;
               }

               var2 = var0.getAnchor(var1 - 2);
            }

            return var2;
         }
      }
   }

   public static long getLastAnchorId(ITextDocumentPart var0) {
      IAnchor var1 = getLastAnchor(var0);
      return var1 == null ? -1L : var1.getIdentifier();
   }

   public static long getNextAnchorId(ITextDocumentPart var0) {
      int var1 = var0.getCountOfAnchors();
      if (var1 == 0) {
         return -1L;
      } else {
         IAnchor var2 = var0.getAnchor(var1 - 1);
         return var2.getLineIndex() == var0.getCountOfLines() ? var2.getIdentifier() : var2.getIdentifier() + 1L;
      }
   }

   public static List getItems(ITextDocumentPart var0) {
      ArrayList var1 = new ArrayList();

      for (ILine var3 : var0.getLines()) {
         var1.addAll(var3.getItems());
      }

      return var1;
   }

   public static ITextItem getItemAt(ITextDocumentPart var0, int var1, int var2) {
      return var1 >= 0 && var1 < var0.getCountOfLines() ? getItemAt(var0.getLine(var1), var2) : null;
   }

   public static ITextItem getItemAt(ILine var0, int var1) {
      for (ITextItem var3 : var0.getItems()) {
         int var4 = var3.getOffset();
         if (var1 < var4) {
            break;
         }

         if (var1 < var4 + var3.getLength()) {
            return var3;
         }
      }

      return null;
   }

   public static String buildRawTextFromPart(ITextDocumentPart var0) {
      ArrayList var1 = new ArrayList();

      for (ILine var3 : var0.getLines()) {
         CharSequence var4 = var3.getText();
         var1.add(var4);
      }

      return new CharSequenceList(var1).toString();
   }

   public static String buildRawTextFromPartInterruptibly(ITextDocumentPart var0) throws InterruptedException {
      ArrayList var1 = new ArrayList();
      int var2 = 0;

      for (ILine var4 : var0.getLines()) {
         CharSequence var5 = var4.getText();
         var1.add(var5);
         if (++var2 % 1000 == 0 && Thread.interrupted()) {
            throw new InterruptedException("The text part generation was interrupted");
         }
      }

      return new CharSequenceList(var1).toString();
   }

   public static List getLinesOfAnchor(ITextDocumentPart var0, long var1) {
      List var3 = var0.getAnchors();
      int var4 = 0;

      for (IAnchor var6 : var3) {
         if (var6.getIdentifier() == var1) {
            break;
         }

         var4++;
      }

      if (var4 >= var3.size()) {
         return null;
      } else {
         int var7 = ((IAnchor)var3.get(var4)).getLineIndex();
         int var8;
         if (var4 + 1 >= var3.size()) {
            var8 = var0.getCountOfLines();
         } else {
            var8 = ((IAnchor)var3.get(var4 + 1)).getLineIndex();
         }

         return var0.getLines().subList(var7, var8);
      }
   }

   public static ILine getLineAt(ITextDocumentPart var0, ICoordinates var1) {
      List var2 = getLinesOfAnchor(var0, var1.getAnchorId());
      return var2 != null && var1.getLineDelta() < var2.size() ? (ILine)var2.get(var1.getLineDelta()) : null;
   }

   public static IAnchor getAnchorAtLine(ITextDocumentPart var0, int var1) {
      IAnchor var2 = null;

      for (IAnchor var4 : var0.getAnchors()) {
         if (var1 < var4.getLineIndex()) {
            break;
         }

         var2 = var4;
      }

      return var2;
   }

   public static boolean isInsidePart(ITextDocumentPart var0, ICoordinates var1) {
      IAnchor var2 = getAnchorById(var0, var1.getAnchorId());
      if (var2 == null) {
         return false;
      } else {
         int var3 = var2.getLineIndex() + var1.getLineDelta();
         if (var3 >= 0 && var3 < var0.getCountOfLines()) {
            ILine var4 = var0.getLine(var3);
            int var5 = var1.getColumnOffset();
            return var5 >= 0 && var5 <= var4.getText().length();
         } else {
            return false;
         }
      }
   }

   public static int coordinatesToLineIndex(ITextDocumentPart var0, ICoordinates var1) {
      if (var1 == null) {
         return -1;
      } else {
         IAnchor var2 = getAnchorById(var0, var1.getAnchorId());
         if (var2 == null) {
            if (var1.getAnchorId() <= getFirstAnchorId(var0) || var1.getAnchorId() >= getLastAnchorId(var0)) {
               return -1;
            }

            var2 = getApproximateAnchorById(var0, var1.getAnchorId(), -1);
         }

         int var3 = var2.getLineIndex() + var1.getLineDelta();
         return var3 >= 0 && var3 < var0.getCountOfLines() ? var3 : -1;
      }
   }

   public static IAnchor getAnchorById(ITextDocumentPart var0, long var1) {
      for (IAnchor var4 : var0.getAnchors()) {
         long var5 = var4.getIdentifier();
         if (var5 == var1) {
            return var4;
         }

         if (var5 > var1) {
            break;
         }
      }

      return null;
   }

   public static IAnchor getNearestAnchorById(ITextDocumentPart var0, long var1) {
      return getApproximateAnchorById(var0, var1, 0);
   }

   public static IAnchor getApproximateAnchorById(ITextDocumentPart var0, long var1, int var3) {
      List var4 = var0.getAnchors();
      IAnchor var6 = null;
      long var7 = -1L;

      int var5;
      for (var5 = 0; var5 < var4.size(); var5++) {
         var6 = (IAnchor)var4.get(var5);
         var7 = var6.getIdentifier();
         if (var7 >= var1) {
            break;
         }
      }

      if (var6 == null) {
         return null;
      } else if (var7 == var1) {
         return var6;
      } else if (var5 >= var4.size() || var5 == 0) {
         return null;
      } else if (var3 > 0) {
         return var6;
      } else {
         IAnchor var9 = (IAnchor)var4.get(var5 - 1);
         if (var3 < 0) {
            return var9;
         } else {
            long var10 = var7 - var1;
            long var12 = var1 - var9.getIdentifier();
            return var10 <= var12 ? var6 : var9;
         }
      }
   }

   public static boolean isAnchorDisplayed(ITextDocumentPart var0, long var1) {
      return getAnchorById(var0, var1) != null;
   }

   public static List getObjectsAt(ITextDocumentPart var0, int var1, int var2) {
      ArrayList var3 = new ArrayList();

      for (IObjectLocation var5 : var0.getObjectLocations()) {
         if (var1 >= var5.getLineBegin() && var1 <= var5.getLineEnd()) {
            if (var1 == var5.getLineBegin() && var2 < var5.getColumnBegin() || var1 == var5.getLineEnd() && var2 >= var5.getColumnEnd()) {
               continue;
            }

            var3.add(var5.getObject());
         }

         if (var5.getLineBegin() > var1) {
            break;
         }
      }

      return var3;
   }

   public static Iterable iterateLines(ITextDocumentPart var0) {
      return new TextPartUtil$1(var0);
   }

   public static class LineInfo {
      int lineindex;
      long anchorid;
      int anchoredLineindex;

      public LineInfo(int var1, long var2, int var4) {
         this.lineindex = var1;
         this.anchorid = var2;
         this.anchoredLineindex = var4;
      }

      public int getPartLineIndex() {
         return this.lineindex;
      }

      public long getAnchorId() {
         return this.anchorid;
      }

      public int getAnchoredLineIndex() {
         return this.anchoredLineindex;
      }

      @Override
      public String toString() {
         return Strings.ff("lineIndex=%d {anchorId=%d, anchoredLineindex=%d}", this.lineindex, this.anchorid, this.anchoredLineindex);
      }
   }

   static class LineIterator implements Iterator {
      private List anchors;
      private int ianchor;
      private int anchorcount;
      private List lines;
      private int iline;
      private int linecount;
      private long currentAnchorId;
      private int currentAnchorLineStart;
      private int nextAnchorLineStart;

      LineIterator(ITextDocumentPart var1) {
         this.anchors = var1.getAnchors();
         this.ianchor = 0;
         this.anchorcount = this.anchors.size();
         this.lines = var1.getLines();
         this.iline = 0;
         this.linecount = this.lines.size();
         if (this.anchorcount >= 1) {
            this.currentAnchorId = ((IAnchor)this.anchors.get(0)).getIdentifier();
            this.currentAnchorLineStart = ((IAnchor)this.anchors.get(0)).getLineIndex();
            Assert.a(((IAnchor)this.anchors.get(0)).getLineIndex() == 0);
            if (this.anchorcount >= 2) {
               this.nextAnchorLineStart = ((IAnchor)this.anchors.get(1)).getLineIndex();
            } else {
               this.nextAnchorLineStart = -1;
            }
         } else {
            Assert.a(this.linecount == 0);
            this.currentAnchorId = 0L;
            this.nextAnchorLineStart = -1;
         }
      }

      @Override
      public boolean hasNext() {
         return this.iline < this.linecount;
      }

      public TextPartUtil.LineInfo next() {
         TextPartUtil.LineInfo var1 = new TextPartUtil.LineInfo(this.iline, this.currentAnchorId, this.iline - this.currentAnchorLineStart);
         this.iline++;
         if (this.iline >= this.nextAnchorLineStart) {
            this.ianchor++;
            if (this.ianchor < this.anchorcount) {
               this.currentAnchorLineStart = this.nextAnchorLineStart;
               this.currentAnchorId = ((IAnchor)this.anchors.get(this.ianchor)).getIdentifier();
               if (this.ianchor + 1 < this.anchorcount) {
                  this.nextAnchorLineStart = ((IAnchor)this.anchors.get(this.ianchor + 1)).getLineIndex();
               } else {
                  this.nextAnchorLineStart = -1;
               }
            } else {
               this.nextAnchorLineStart = -1;
            }
         }

         return var1;
      }
   }
}
