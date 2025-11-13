package com.pnfsoftware.jeb.core.output.code;

import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.units.impl.Comment;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Collection;
import java.util.stream.Collectors;

public class CommentGenerator {
   private CodeDocumentPart out;
   private String pfx;
   private String pfxInline;
   private int margin;
   private int i;
   private int actualMargin;

   public CommentGenerator(CodeDocumentPart var1, String var2) {
      this(var1, var2, 2, 1);
   }

   public CommentGenerator(CodeDocumentPart var1, String var2, int var3, int var4) {
      if (var1 != null && var2 != null && var3 >= 0 && var4 >= 0) {
         this.out = var1;
         this.pfx = var2 + Strings.spaces(var4);
         this.pfxInline = Strings.spaces(var3);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public void setMargin(int var1) {
      this.margin = var1;
   }

   public int getMargin() {
      return this.margin;
   }

   public void reset() {
      this.margin = 0;
      this.i = 0;
      this.actualMargin = 0;
   }

   public boolean genInline(ICodeCoordinates var1, String var2, boolean var3) {
      Assert.a(this.i == 0);
      if (var2 != null && !var2.isEmpty()) {
         this.ac(var1, var2, true, var3);
      }

      return this.i > 0;
   }

   public boolean genInline(ICodeCoordinates var1, Comment var2, int var3, int var4, String var5) {
      Assert.a(this.i == 0);
      if (var2 != null) {
         String var6 = var2.getInline();
         if (var6 != null) {
            this.ac(var1, var6, true, false);
         }

         Collection var7 = var2.getMetaComments(var3, var4);
         if (!var7.isEmpty()) {
            var6 = Strings.join("\n", (Iterable)var7.stream().map(var0 -> var0.toString()).collect(Collectors.toList()));
            this.ac(var1, var6, true, true);
         }
      }

      if (var5 != null && !var5.isEmpty()) {
         this.ac(var1, var5, true, true);
      }

      return this.i > 0;
   }

   public boolean genPre(ICodeCoordinates var1, Comment var2, String var3) {
      Assert.a(this.i == 0);
      if (var2 != null) {
         String var4 = var2.getPre();
         if (var4 != null) {
            this.ac(var1, var2.getPre(), false, false);
         }
      }

      if (var3 != null && !var3.isEmpty()) {
         this.ac(var1, var3, true, true);
      }

      if (this.i <= 0) {
         return false;
      } else {
         this.out.eol();
         return true;
      }
   }

   private void ac(ICodeCoordinates var1, String var2, boolean var3, boolean var4) {
      if (this.i == 0) {
         if (this.margin > 0) {
            int var5 = this.margin - this.out.getCurrentLineLength();
            if (var5 > 0) {
               this.out.space(var5);
            }
         }

         this.actualMargin = this.out.getCurrentLineLength();
      }

      for (String var8 : Strings.splitLines(var2)) {
         if (this.i >= 1) {
            this.out.eol(var1);
            this.out.space(this.actualMargin);
         }

         if (var3) {
            this.out.append(this.pfxInline);
         }

         this.out.appendComment(this.pfx + var8, var4);
         this.i++;
      }
   }
}
