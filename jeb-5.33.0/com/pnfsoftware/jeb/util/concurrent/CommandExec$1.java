package com.pnfsoftware.jeb.util.concurrent;

class CommandExec$1 extends MonitorInfoAdapter {
   CommandExec$1(CommandExec var1, long var2, long var4, CommandRoutine var6) {
      super(var2, var4);
      this.this$0 = var1;
      this.val$routine = var6;
   }

   @Override
   public void onInterrupt() {
      this.val$routine.killProcess();
   }
}
