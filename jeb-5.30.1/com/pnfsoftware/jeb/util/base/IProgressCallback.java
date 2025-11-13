package com.pnfsoftware.jeb.util.base;

public interface IProgressCallback {
   default boolean isInitialized() {
      return this.getTotal() > 0L;
   }

   void setTotal(long var1);

   long getTotal();

   void updateTotal(long var1);

   void setCurrent(long var1);

   long getCurrent();

   long increment();

   default void message(String var1) {
      this.message(0, var1);
   }

   void message(int var1, String var2);
}
