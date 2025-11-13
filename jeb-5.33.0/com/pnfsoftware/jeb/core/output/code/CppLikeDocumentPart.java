package com.pnfsoftware.jeb.core.output.code;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Arrays;
import java.util.List;

public class CppLikeDocumentPart extends CodeDocumentPart {
   protected String commentBlockBegin = "/*";
   protected String commentBlockEnd = "*/";
   protected String commentInline = "//";
   private ICodeCoordinates eol_coord;
   private String eol_comment;
   private boolean eol_comment_is_auto;

   public CppLikeDocumentPart(long var1) {
      super(var1);
   }

   public void setEolCoordinates(ICodeCoordinates var1) {
      this.eol_coord = var1;
   }

   public ICodeCoordinates getEolCoordinates() {
      return this.eol_coord;
   }

   public void setEolComment(String var1) {
      this.eol_comment = var1;
      this.eol_comment_is_auto = false;
   }

   public void setEolComment(String var1, boolean var2) {
      this.eol_comment = var1;
      this.eol_comment_is_auto = var2;
   }

   public String getEolComment() {
      return this.eol_comment;
   }

   public void onEolAddComment(String var1) {
      if (this.eol_comment == null) {
         this.eol_comment = var1;
      } else {
         this.eol_comment = this.eol_comment + " / " + var1;
      }
   }

   @Override
   public final void eol() {
      this.eol(this.eol_coord);
   }

   @Override
   public final void eol(ICodeCoordinates var1) {
      this.eol_coord = null;
      if (this.eol_comment != null) {
         String var2 = this.eol_comment;
         this.eol_comment = null;
         this.eolComment(var2, var1, false, this.eol_comment_is_auto);
      }

      super.eol(var1);
   }

   private void eolComment(String var1, ICodeCoordinates var2, boolean var3, boolean var4) {
      String var5 = this.commentInline + " ";
      if (var3) {
         this.space(2);
         var1 = var5 + var1.replace("\r\n", " / ").replace("\r", "").replace("\n", " / ");
         this.appendComment(var1, var4);
      } else {
         int var6 = this.getCurrentLineLength() - this.getCurrentMarginLength();
         List var7;
         if (var4) {
            var7 = Formatter.wordWrap(var1, 80, 1);
         } else {
            var7 = Arrays.asList(Strings.splitLines(var1));
         }

         int var8 = 0;

         for (String var10 : var7) {
            String var11 = "";
            if (var8 > 0) {
               super.eol(var2);
               var11 = Strings.spaces(var6);
            }

            this.space(2);
            var11 = var11 + var5 + var10;
            this.appendComment(var11, var4);
            var8++;
         }
      }
   }

   public void appendMultiLineCommentAuto(String var1, boolean var2, boolean var3) {
      this.appendMultiLineComment(var1, true, var2, var3);
   }

   public void appendMultiLineComment(String var1, boolean var2, boolean var3) {
      this.appendMultiLineComment(var1, false, var2, var3);
   }

   private void appendMultiLineComment(String var1, boolean var2, boolean var3, boolean var4) {
      if (var1 != null && !var1.isEmpty()) {
         ItemClassIdentifiers var5 = var2 ? ItemClassIdentifiers.COMMENT_AUTO : ItemClassIdentifiers.COMMENT;
         int var6 = this.getCurrentLineLength() - this.getCurrentMarginLength();
         String[] var7 = Strings.splitLines(var1);
         this.eol_comment = null;
         if (var3 && var7.length > 1) {
            this.appendAndRecord(this.commentBlockBegin, var5);

            for (String var17 : var7) {
               this.eolSaveCoord();
               this.space(var6);
               this.appendAndRecord(var17, var5);
            }

            this.eolSaveCoord();
            this.space(var6);
            this.appendAndRecord(this.commentBlockEnd, var5);
         } else {
            int var8 = 0;

            for (String var12 : var7) {
               if (var8 >= 1) {
                  ICodeCoordinates var13 = this.eol_coord;
                  this.eolSaveCoord();
                  this.eol_coord = var13;
                  this.space(var6);
               }

               this.appendAndRecord(this.commentInline + " " + var12, var5);
               var8++;
            }
         }

         if (var4) {
            this.eol();
         }
      }
   }

   private void eolSaveCoord() {
      ICodeCoordinates var1 = this.eol_coord;
      this.eol();
      this.eol_coord = var1;
   }

   @Override
   public void validate() {
      super.validate();
   }
}
