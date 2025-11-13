package com.pnfsoftware.jeb.core.units.code.debug.impl;

import com.pnfsoftware.jeb.core.units.code.debug.DebuggerConnectorClass;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerMachineInformation;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerProcessInformation;
import com.pnfsoftware.jeb.util.format.Strings;

public class DebuggerSetupInformation {
   private DebuggerConnectorClass connectorClass;
   private IDebuggerMachineInformation machine;
   private IDebuggerProcessInformation process;
   private String hostname;
   private int port;
   private boolean suspendThreads;
   private boolean useChidrenDebuggers;
   private boolean pretendDebuggable;
   private Long actualImageBase;

   public static DebuggerSetupInformation create(IDebuggerMachineInformation var0, IDebuggerProcessInformation var1) {
      DebuggerSetupInformation var2 = new DebuggerSetupInformation();
      var2.connectorClass = DebuggerConnectorClass.PROCESS;
      var2.machine = var0;
      var2.process = var1;
      return var2;
   }

   public static DebuggerSetupInformation create(String var0, int var1) {
      DebuggerSetupInformation var2 = new DebuggerSetupInformation();
      var2.connectorClass = DebuggerConnectorClass.PORT;
      var2.hostname = var0;
      var2.port = var1;
      return var2;
   }

   private DebuggerSetupInformation() {
   }

   public DebuggerConnectorClass getConnectorClass() {
      return this.connectorClass;
   }

   public IDebuggerMachineInformation getMachine() {
      return this.machine;
   }

   public IDebuggerProcessInformation getProcess() {
      return this.process;
   }

   public String getHostname() {
      return this.hostname;
   }

   public int getPort() {
      return this.port;
   }

   public boolean doSuspendThreads() {
      return this.suspendThreads;
   }

   public void setSuspendThreads(boolean var1) {
      this.suspendThreads = var1;
   }

   public boolean shouldUseChildrenDebuggers() {
      return this.useChidrenDebuggers;
   }

   public void setUseChildrenDebuggers(boolean var1) {
      this.useChidrenDebuggers = var1;
   }

   public boolean isPretendDebuggable() {
      return this.pretendDebuggable;
   }

   public void setPretendDebuggable(boolean var1) {
      this.pretendDebuggable = var1;
   }

   public Long getActualImageBase() {
      return this.actualImageBase;
   }

   public void setActualImageBase(Long var1) {
      this.actualImageBase = var1;
   }

   @Override
   public String toString() {
      String var1 = Strings.ff("%s=", this.connectorClass);

      return switch (this.connectorClass) {
         case PROCESS -> var1 + Strings.ff("%s:%s", this.machine, this.process);
         case PORT -> var1 + Strings.ff("%s:%s", this.hostname, this.port);
         default -> var1 + "?";
      } + Strings.ff("(susp=%b,usechld=%b)", this.suspendThreads, this.useChidrenDebuggers);
   }
}
