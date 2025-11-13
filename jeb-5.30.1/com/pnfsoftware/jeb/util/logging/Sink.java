package com.pnfsoftware.jeb.util.logging;

public abstract class Sink {
   protected Object output;
   private int logLevel;
   private boolean prefixWithTimestamp;

   protected Sink(Object var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Output for sink cannot be null");
      } else {
         this.output = var1;
      }
   }

   public Object getOutput() {
      return this.output;
   }

   public int getLogLevel() {
      return this.logLevel;
   }

   public void setLogLevel(int var1) {
      this.logLevel = var1;
   }

   public boolean isPrefixWithTimestamp() {
      return this.prefixWithTimestamp;
   }

   public void setPrefixWithTimestamp(boolean var1) {
      this.prefixWithTimestamp = var1;
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.output == null ? 0 : this.output.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         Sink var2 = (Sink)var1;
         if (this.output == null) {
            if (var2.output != null) {
               return false;
            }
         } else if (!this.output.equals(var2.output)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return this.output.toString();
   }
}
