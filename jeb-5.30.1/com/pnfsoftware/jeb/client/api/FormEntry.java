package com.pnfsoftware.jeb.client.api;

import java.util.function.Predicate;

public class FormEntry {
   public static final int INLINE = 1;
   public String header;
   public Object defaultValue;
   public int flags;
   public Predicate validator;

   public FormEntry() {
   }

   public FormEntry(String var1, Object var2, int var3, Predicate var4) {
      this.header = var1;
      this.defaultValue = var2;
      this.flags = var3;
      this.validator = var4;
   }

   public static class Text extends FormEntry {
      public int minColumnCount;
      public int minLineCount;

      public Text() {
      }

      public Text(String var1, String var2, int var3, Predicate var4, int var5, int var6) {
         super(var1, var2, var3, var4);
         this.minColumnCount = var5;
         this.minLineCount = var6;
      }
   }
}
