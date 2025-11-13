package com.pnfsoftware.jeb.core.actions;

public class ActionReplaceData extends ActionData {
   private Object o;
   private Object repl;

   public void setTargetObject(Object var1) {
      this.o = var1;
   }

   public Object getTargetObject() {
      return this.o;
   }

   public void setWantedReplacement(Object var1) {
      this.repl = var1;
   }

   public Object getWantedReplacement() {
      return this.repl;
   }
}
