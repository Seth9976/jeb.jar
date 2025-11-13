package com.pnfsoftware.jeb.core.output.text;

public class ObjectLocation implements IObjectLocation {
   private Object object;
   private int depth;
   public int lineBegin;
   private int columnBegin;
   public int lineEnd = -1;
   private int columnEnd;

   public ObjectLocation(Object var1, int var2, int var3, int var4) {
      this.object = var1;
      this.depth = var2;
      this.lineBegin = var3;
      this.columnBegin = var4;
   }

   public void setEnd(int var1, int var2) {
      this.lineEnd = var1;
      this.columnEnd = var2;
   }

   @Override
   public Object getObject() {
      return this.object;
   }

   public int getDepth() {
      return this.depth;
   }

   @Override
   public int getLineBegin() {
      return this.lineBegin;
   }

   @Override
   public int getLineEnd() {
      return this.lineEnd;
   }

   @Override
   public int getColumnBegin() {
      return this.columnBegin;
   }

   @Override
   public int getColumnEnd() {
      return this.columnEnd;
   }
}
