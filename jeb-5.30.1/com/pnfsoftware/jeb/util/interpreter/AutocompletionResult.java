package com.pnfsoftware.jeb.util.interpreter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AutocompletionResult {
   public static final AutocompletionResult EMPTY = new AutocompletionResult();
   private List list;
   private char lastSeparator;

   public AutocompletionResult() {
      this.list = new ArrayList();
   }

   public AutocompletionResult(List var1) {
      this(var1, ' ');
   }

   public AutocompletionResult(char var1) {
      this(null, var1);
   }

   public AutocompletionResult(List var1, char var2) {
      if (var1 == null) {
         var1 = new ArrayList();
      }

      if (var2 == 0) {
         var2 = ' ';
      }

      this.list = (List)var1;
      this.lastSeparator = var2;
   }

   public boolean add(String var1) {
      if (var1 != null && !this.list.contains(var1)) {
         this.list.add(var1);
         return true;
      } else {
         return false;
      }
   }

   public void addAll(Collection var1) {
      for (String var3 : var1) {
         this.add(var3);
      }
   }

   public List getAutocompletes() {
      return this.list;
   }

   public char getLastSeparator() {
      return this.lastSeparator;
   }

   @Override
   public String toString() {
      return this.list.toString();
   }

   public static List filterStartsWith(String var0, List var1) {
      ArrayList var2 = new ArrayList();

      for (String var4 : var1) {
         if (var4.startsWith(var0)) {
            var2.add(var4);
         }
      }

      return var2;
   }
}
