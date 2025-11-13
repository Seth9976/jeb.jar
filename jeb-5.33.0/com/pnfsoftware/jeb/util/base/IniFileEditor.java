package com.pnfsoftware.jeb.util.base;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class IniFileEditor {
   private static final ILogger logger = GlobalLog.getLogger(IniFileEditor.class);
   File file;
   Character cc;
   List lines;

   public IniFileEditor(File var1, Character var2) throws IOException {
      this.file = var1;
      this.cc = var2;
      this.lines = IO.readLines(var1, Charset.defaultCharset());
   }

   public IniFileEditor(File var1) throws IOException {
      this(var1, null);
   }

   public IniFileEditor(String var1, Character var2) {
      this.file = null;
      this.cc = var2;
      this.lines = new ArrayList(Arrays.asList(Strings.splitLines(var1)));
   }

   public IniFileEditor(String var1) {
      this(var1, null);
   }

   public void setCommentChar(Character var1) {
      this.cc = var1;
   }

   public Character getCommantChar() {
      return this.cc;
   }

   public void update() throws IOException {
      this.write(this.file);
   }

   public void write(File var1) throws IOException {
      IO.writeLines(var1, this.lines, Charset.defaultCharset());
   }

   public List getSectionNames() {
      ArrayList var1 = new ArrayList();

      for (int var2 = 0; var2 < this.lines.size(); var2++) {
         String var3 = ((String)this.lines.get(var2)).trim();
         if (var3.startsWith("[") && var3.endsWith("]")) {
            var1.add(var3.substring(1, var3.length() - 1));
         }
      }

      return var1;
   }

   public List getSectionContents(String var1) {
      int[] var2 = this.getSection(var1, false);
      if (var2 == null) {
         return Collections.emptyList();
      } else {
         ArrayList var3 = new ArrayList();

         for (String var5 : this.lines.subList(var2[0], var2[1])) {
            var5 = var5.trim();
            if (!var5.isEmpty()) {
               var3.add(var5);
            }
         }

         return var3;
      }
   }

   public Map getSectionKeyValues(String var1) {
      int[] var2 = this.getSection(var1, false);
      if (var2 == null) {
         return Collections.emptyMap();
      } else {
         LinkedHashMap var3 = new LinkedHashMap();

         for (String var5 : this.lines.subList(var2[0], var2[1])) {
            var5 = var5.trim();
            if (!var5.isEmpty() && (this.cc == null || var5.charAt(0) != this.cc)) {
               String[] var6 = var5.split("=", 2);
               if (var6.length == 2) {
                  var3.put(var6[0].trim(), var6[1].trim());
               } else {
                  logger.warn(S.L("Illegal key-value line in ini file: %s"), var5);
               }
            }
         }

         return var3;
      }
   }

   public void setSectionContents(String var1, List var2) {
      int[] var3 = this.getSection(var1, true);
      int var4 = var3[0];
      int var5 = var3[1];
      if (var4 < this.lines.size()) {
         int var6 = var5 - var4;

         while (var6-- > 0) {
            this.lines.remove(var4);
         }
      }

      for (String var7 : var2) {
         var7 = var7.trim();
         if (!var7.isEmpty()) {
            this.lines.add(var4, var7);
            var4++;
         }
      }

      this.lines.add(var4, "");
   }

   private int[] getSection(String var1, boolean var2) {
      if (var1 == null) {
         int var8;
         for (var8 = 0; var8 < this.lines.size(); var8++) {
            String var9 = ((String)this.lines.get(var8)).trim();
            if (var9.startsWith("[")) {
               break;
            }
         }

         return new int[]{0, var8};
      } else {
         String var3 = "[" + var1 + "]";
         int var4 = -1;
         int var5 = -1;

         for (int var6 = 0; var6 < this.lines.size(); var6++) {
            String var7 = ((String)this.lines.get(var6)).trim();
            if (var7.equals(var3)) {
               var4 = var6 + 1;
               break;
            }
         }

         if (var4 < 0) {
            if (!var2) {
               return null;
            }

            this.lines.add(var3);
            var4 = this.lines.size();
         }

         for (int var10 = var4; var10 < this.lines.size(); var10++) {
            String var11 = ((String)this.lines.get(var10)).trim();
            if (var11.startsWith("[")) {
               var5 = var10;
               break;
            }
         }

         if (var5 < 0) {
            var5 = this.lines.size();
         }

         return new int[]{var4, var5};
      }
   }

   public String getValue(String var1) {
      return this.getValue(null, var1, "=");
   }

   public String getValue(String var1, String var2) {
      return this.getValue(var1, var2, "=");
   }

   public String getValue(String var1, String var2, String var3) {
      for (String var5 : this.getSectionContents(var1)) {
         int var6 = var5.indexOf(var3);
         if (var6 >= 0) {
            String var7 = var5.substring(0, var6).trim();
            if (var7.equals(var2)) {
               return var5.substring(var6 + var3.length()).trim();
            }
         }
      }

      return null;
   }

   public boolean setValue(String var1, String var2, String var3) {
      return this.setValue(var1, var2, var3, "=");
   }

   public boolean setValue(String var1, String var2, String var3, String var4) {
      List var5 = this.getSectionContents(var1);

      int var6;
      for (var6 = 0; var6 < var5.size(); var6++) {
         String var7 = (String)var5.get(var6);
         int var8 = var7.indexOf(var4);
         if (var8 >= 0) {
            String var9 = var7.substring(0, var8).trim();
            if (var9.equals(var2)) {
               String var10 = var7.substring(var8 + var4.length()).trim();
               if (var10.equals(var3)) {
                  return false;
               }

               var5.set(var6, var2 + var4 + var3);
               break;
            }
         }
      }

      if (var6 >= var5.size()) {
         var5.add(var2 + var4 + var3);
      }

      this.setSectionContents(var1, var5);
      return true;
   }
}
