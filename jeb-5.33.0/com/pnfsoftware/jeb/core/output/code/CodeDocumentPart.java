package com.pnfsoftware.jeb.core.output.code;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.coordinates.CodeCoordinatesUtil;
import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.output.text.ITextDocumentPart;
import com.pnfsoftware.jeb.core.output.text.ObjectLocation;
import com.pnfsoftware.jeb.core.output.text.impl.TextMark;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class CodeDocumentPart implements ICodeDocument, ITextDocumentPart {
   private List lines;
   private List anchors;
   private long currentAnchorId;
   private boolean padding;
   private String paddingString;
   private int indentLevel;
   private String indentString;
   private Deque linecoordstack = new ArrayDeque();
   private Deque coordstack = new ArrayDeque();
   private int sepCounter = 0;
   private Deque sepStack = new ArrayDeque();
   private String parameterSeparator = ", ";
   private List keywords = new ArrayList();
   private static boolean tokenFlatteningEnabled;
   private static final Set noEscape = new HashSet();
   private List objlist = new ArrayList();

   public CodeDocumentPart(long var1) {
      if (var1 < 0L) {
         throw new IllegalArgumentException("Invalid anchor identifier: " + var1);
      } else {
         this.padding = false;
         this.paddingString = "          ";
         this.indentLevel = 0;
         this.indentString = "    ";
         this.lines = new ArrayList();
         this.lines.add(new CodeLine());
         this.anchors = new ArrayList();
         this.currentAnchorId = var1;
      }
   }

   @Override
   public List getLines() {
      return this.lines.subList(0, this.lines.size() - 1);
   }

   @Override
   public int getCountOfLines() {
      return this.lines.size() - 1;
   }

   public CodeLine getLine(int var1) {
      if (var1 >= 0 && var1 + 1 < this.lines.size()) {
         return (CodeLine)this.lines.get(var1);
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   @Override
   public List getAnchors() {
      return this.anchors;
   }

   public CodeAnchor getAnchor(int var1) {
      return (CodeAnchor)this.anchors.get(var1);
   }

   @Override
   public int getCountOfAnchors() {
      return this.anchors.size();
   }

   private boolean canRegisterAnchor(long var1, int var3) {
      if (var1 >= 0L && var3 >= 0) {
         if (!this.anchors.isEmpty()) {
            CodeAnchor var4 = (CodeAnchor)this.anchors.get(this.anchors.size() - 1);
            if (var4.getIdentifier() >= var1) {
               return false;
            }

            if (var4.getLineIndex() >= var3) {
               return false;
            }
         }

         return true;
      } else {
         throw new IllegalArgumentException(Strings.ff("Can not register anchor id %d with line index %d: need positive values.", var1, var3));
      }
   }

   public boolean registerAnchor(String var1) {
      int var2 = this.getCurrentLineIndex();
      if (!this.canRegisterAnchor(this.currentAnchorId, var2)) {
         return false;
      } else {
         this.anchors.add(new CodeAnchor(this.currentAnchorId, var2, var1));
         this.currentAnchorId++;
         return true;
      }
   }

   public boolean registerAnchor(long var1, String var3) {
      int var4 = this.getCurrentLineIndex();
      if (!this.canRegisterAnchor(var1, var4)) {
         return false;
      } else {
         this.anchors.add(new CodeAnchor(var1, var4, var3));
         this.currentAnchorId = var1 + 1L;
         return true;
      }
   }

   public CodeAnchor getCurrentAnchor() {
      return (CodeAnchor)this.anchors.get(this.anchors.size() - 1);
   }

   public int getLineCount() {
      return this.lines.size() - 1;
   }

   public int getCurrentLineIndex() {
      return this.lines.size() - 1;
   }

   public int getLastLineIndex() {
      return this.lines.size() - 2;
   }

   public CodeLine getCurrentLine() {
      return (CodeLine)this.lines.get(this.lines.size() - 1);
   }

   public int getCurrentLineLength() {
      return this.getCurrentLine().getText().length();
   }

   public boolean isCurrentLineEmpty() {
      return this.getCurrentLine().isEmpty();
   }

   public void setIndentationString(String var1) {
      this.indentString = var1;
   }

   public String getIndentationString() {
      return this.indentString;
   }

   public void setPaddingString(String var1) {
      this.paddingString = var1;
   }

   public void resetPaddingString() {
      this.paddingString = "";
   }

   public void enablePadding() {
      this.padding = true;
   }

   public void disablePadding() {
      this.padding = false;
   }

   public void indentReset() {
      this.indentLevel = 0;
   }

   public int getIndentationLevel() {
      return this.indentLevel;
   }

   public void setIndentationLevel(int var1) {
      this.indentLevel = var1;
   }

   public void incrementIndentationLevel() {
      this.indentLevel++;
   }

   public void decrementIndentationLevel() {
      this.indentLevel--;
   }

   public int getCurrentMarginLength() {
      int var1 = 0;
      if (this.padding && this.paddingString != null) {
         var1 += this.paddingString.length();
      }

      if (this.indentLevel > 0 && this.indentString != null) {
         var1 += this.indentLevel * this.indentString.length();
      }

      return var1;
   }

   public static boolean enableTokenFlattening(boolean var0) {
      boolean var1 = tokenFlatteningEnabled;
      tokenFlatteningEnabled = var0;
      return var1;
   }

   public void append(String var1, int[] var2) {
      var1 = Strings.replaceNewLines(var1, "/");
      if (tokenFlatteningEnabled) {
         var1 = Formatter.escapeString(var1, 0, true, noEscape);
      }

      CodeLine var3 = this.getCurrentLine();
      if (var3.isEmpty()) {
         if (this.padding) {
            var3.append(this.paddingString);
         }

         for (int var4 = 0; var4 < this.indentLevel; var4++) {
            var3.append(this.indentString);
         }
      }

      if (var2 != null) {
         var2[0] = var3.length();
      }

      var3.append(var1);
   }

   @Override
   public void append(String var1) {
      this.append(var1, (int[])null);
   }

   @Override
   public void append(String var1, ItemClassIdentifiers var2) {
      this.appendAndRecord(var1, var2);
   }

   private void appendSafe(CharSequence var1) {
      CodeLine var2 = this.getCurrentLine();
      if (var2.isEmpty()) {
         if (this.padding) {
            var2.append(this.paddingString);
         }

         for (int var3 = 0; var3 < this.indentLevel; var3++) {
            var2.append(this.indentString);
         }
      }

      var2.append(var1);
   }

   public void appendMulti(String var1) {
      int var2 = 0;

      for (String var6 : Strings.splitLines(var1)) {
         if (var2 >= 1) {
            this.eol();
         }

         this.append(var6);
         var2++;
      }
   }

   public void space() {
      this.appendSafe(" ");
   }

   public void space(int var1) {
      switch (var1) {
         case 1:
            this.appendSafe(" ");
            break;
         case 2:
            this.appendSafe("  ");
            break;
         case 3:
            this.appendSafe("   ");
            break;
         case 4:
            this.appendSafe("    ");
            break;
         case 5:
         case 6:
         case 7:
         case 9:
         default:
            if (var1 > 0) {
               StringBuilder var2 = new StringBuilder();

               for (int var3 = 0; var3 < var1; var3++) {
                  var2.append(' ');
               }

               this.appendSafe(var2);
            }
            break;
         case 8:
            this.appendSafe("        ");
            break;
         case 10:
            this.appendSafe("          ");
      }
   }

   public void setParameterSeparator(String var1) {
      this.parameterSeparator = Strings.replaceNewLines(var1, "/");
   }

   public void appendParameterSeparator() {
      this.appendSafe(this.parameterSeparator);
   }

   protected int registerKeyword(String var1) {
      String var2 = Strings.replaceNewLines(var1, "/");
      this.keywords.add(var2);
      return this.keywords.size() - 1;
   }

   protected void appendKeyword(int var1) {
      this.appendSafe((CharSequence)this.keywords.get(var1));
   }

   public void eol(ICodeCoordinates var1) {
      if (var1 != null) {
         ((CodeLine)this.lines.get(this.lines.size() - 1)).setLineCoordinates(var1);
      }

      this.lines.add(new CodeLine());
      this.registerCurrentCoordinates();
   }

   public void eol() {
      this.eol(null);
   }

   long sep(char var1) {
      long var2 = -5066549580791808L | this.sepCounter;
      this.sepCounter++;
      this.appendAndRecord(var1 + "", ItemClassIdentifiers.SEPARATOR, var2);
      this.sepStack.push(new Couple(var2, var1));
      return var2;
   }

   void sepClose(char var1) {
      Couple var2 = (Couple)this.sepStack.pop();
      long var3 = (Long)var2.getFirst();
      this.appendAndRecord(var1 + "", ItemClassIdentifiers.SEPARATOR, var3);
   }

   @Override
   public void paren() {
      this.sep('(');
   }

   @Override
   public void parenClose() {
      this.sepClose(')');
   }

   @Override
   public void brace() {
      this.sep('{');
   }

   @Override
   public void braceClose() {
      this.sepClose('}');
   }

   @Override
   public void bracket() {
      this.sep('[');
   }

   @Override
   public void bracketClose() {
      this.sepClose(']');
   }

   public void appendAndRecord(String var1, ItemClassIdentifiers var2) {
      this.appendAndRecord(var1, var2, 0L, 0);
   }

   public void appendAndRecord(String var1, ItemClassIdentifiers var2, long var3) {
      this.appendAndRecord(var1, var2, var3, 0);
   }

   public void appendAndRecord(String var1, ItemClassIdentifiers var2, long var3, int var5) {
      if (var1 == null) {
         var1 = Strings.safe(var1);
      }

      int[] var6 = new int[1];
      this.append(var1, var6);
      int var7 = var6[0];
      CodeLine var8 = this.getCurrentLine();
      int var9 = var8.getText().length() - var7;
      AssemblyItem var10 = new AssemblyItem(var7, var9, var2, var3, var5);
      var8.addItem(var10);
   }

   @Override
   public void appendKeyword(String var1) {
      this.appendAndRecord(var1, ItemClassIdentifiers.KEYWORD);
   }

   public void appendKeyword(String var1, long var2) {
      this.appendAndRecord(var1, ItemClassIdentifiers.KEYWORD, var2);
   }

   public void appendComment(String var1) {
      this.appendAndRecord(var1, ItemClassIdentifiers.COMMENT);
   }

   public void appendComment(String var1, boolean var2) {
      this.appendAndRecord(var1, var2 ? ItemClassIdentifiers.COMMENT_AUTO : ItemClassIdentifiers.COMMENT);
   }

   public void appendCommentAuto(String var1) {
      this.appendAndRecord(var1, ItemClassIdentifiers.COMMENT_AUTO);
   }

   public void recordLineCoordinates(ICodeCoordinates var1) {
      this.linecoordstack.push(var1);
   }

   public void unrecordLineCoordinates() {
      this.linecoordstack.pop();
   }

   public ICodeCoordinates getCurrentLineCoordinates() {
      return (ICodeCoordinates)this.linecoordstack.peek();
   }

   public ICodeCoordinates getCurrentMostPreciseCodeCoordinates() {
      ICodeCoordinates var1 = this.getCurrentLineCoordinates();
      ICodeCoordinates var2 = this.getCurrentCoordinates();
      return CodeCoordinatesUtil.getMostAccurate(var1, var2);
   }

   public void recordCurrentCoordinates(ICodeCoordinates var1) {
      this.coordstack.push(var1);
      this.registerCurrentCoordinates();
   }

   public void unrecordCurrentCoordinates() {
      this.coordstack.pop();
      this.registerCurrentCoordinates();
   }

   public ICodeCoordinates getCurrentCoordinates() {
      return (ICodeCoordinates)this.coordstack.peek();
   }

   private void registerCurrentCoordinates() {
   }

   public void validate() {
      if (!this.coordstack.isEmpty()) {
         throw new JebRuntimeException("Invalid coords at anchor " + this.currentAnchorId);
      } else if (!this.isCurrentLineEmpty()) {
         throw new JebRuntimeException("Current line is not empty at anchor " + this.currentAnchorId);
      }
   }

   public void prependCodePart(CodeDocumentPart var1) {
      Long var2 = Long.MAX_VALUE;
      if (!this.anchors.isEmpty()) {
         var2 = ((CodeAnchor)this.anchors.get(0)).getIdentifier();
      }

      ArrayList var3 = new ArrayList(var1.anchors.size());

      for (CodeAnchor var5 : var1.anchors) {
         if (var5.getIdentifier() < var2) {
            var3.add(var5);
         }
      }

      int var7 = var1.getLineCount();

      for (CodeAnchor var6 : this.anchors) {
         var6.adjustLineindex(var7);
      }

      var3.addAll(this.anchors);
      this.anchors = var3;
      ArrayList var9 = new ArrayList(var1.lines.subList(0, var1.lines.size() - 1));
      var9.addAll(this.lines);
      this.lines = var9;
   }

   public boolean moveLine(int var1, int var2) {
      if (this.getLineCount() <= 1) {
         return false;
      } else {
         int var3 = this.getCurrentLineIndex();
         if (var1 != var2 && var1 >= 0 && var1 < var3 && var2 >= 0 && var2 < var3) {
            CodeLine var4 = (CodeLine)this.lines.remove(var1);
            if (var2 < var1) {
               this.lines.add(var2, var4);
            } else {
               this.lines.add(var2 - 1, var4);
            }

            if (var2 < var1) {
               for (ObjectLocation var6 : this.objlist) {
                  if (var6.lineBegin >= var2 && var6.lineBegin < var1) {
                     var6.lineBegin++;
                  }

                  if (var6.lineEnd >= var2 && var6.lineEnd < var1) {
                     var6.lineEnd++;
                  }
               }
            } else {
               for (ObjectLocation var8 : this.objlist) {
                  if (var8.lineBegin > var1 && var8.lineBegin <= var2) {
                     var8.lineBegin--;
                  }

                  if (var8.lineEnd > var1 && var8.lineEnd <= var2) {
                     var8.lineEnd--;
                  }
               }
            }

            return true;
         } else {
            return false;
         }
      }
   }

   public boolean moveLastLine(int var1) {
      return this.moveLine(this.getLastLineIndex(), var1);
   }

   public void markCurrentPosition(String var1, Object var2) {
      this.getCurrentLine().addMark(new TextMark(this.getCurrentLineLength(), var1, var2));
   }

   public String format() {
      StringBuilder var1 = new StringBuilder();

      for (CodeLine var3 : this.lines) {
         var1.append(var3.getText());
         var1.append('\n');
      }

      return var1.toString();
   }

   public void recordObjectLocation(Object var1, boolean var2, int var3) {
      if (this.objlist != null) {
         if (var2) {
            ObjectLocation var4 = new ObjectLocation(var1, var3, this.getCurrentLineIndex(), this.getCurrentLineLength());
            this.objlist.add(var4);
         } else {
            for (int var6 = this.objlist.size() - 1; var6 >= 0; var6--) {
               ObjectLocation var5 = (ObjectLocation)this.objlist.get(var6);
               if (var5.getObject() == var1 && var5.getDepth() == var3 && var5.lineEnd == -1) {
                  var5.setEnd(this.getCurrentLineIndex(), this.getCurrentLineLength());
                  return;
               }
            }

            this.objlist = null;
            RuntimeException var7 = new RuntimeException(Strings.ff("Missing start element for entry: object=%s, depth=%d", var1, var3));
            JebCoreService.notifySilentExceptionToClient(var7);
         }
      }
   }

   @Override
   public List getObjectLocations() {
      return this.objlist == null ? Collections.emptyList() : Collections.unmodifiableList(this.objlist);
   }

   @Override
   public String toString() {
      return Strings.ff("Lines:%d,Current:\"%s\"", this.getLineCount(), this.getCurrentLine().getText());
   }

   static {
      noEscape.add('\'');
      noEscape.add('"');
      noEscape.add('\\');
   }
}
