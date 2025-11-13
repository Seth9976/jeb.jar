package com.pnfsoftware.jeb.core.units.code.debug.impl;

import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerMachineInformation;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Collections;
import java.util.List;

public class DebuggerMachineInformation implements IDebuggerMachineInformation {
   private String name;
   private String location;
   private int flags;
   private String information;

   public DebuggerMachineInformation(String var1, String var2, int var3, String var4) {
      this.name = var1;
      this.location = var2;
      this.flags = var3;
      this.information = var4;
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public String getLocation() {
      return this.location;
   }

   @Override
   public int getFlags() {
      return this.flags;
   }

   @Override
   public String getInformation() {
      return this.information;
   }

   @Override
   public List getProcesses() {
      return Collections.emptyList();
   }

   @Override
   public String toString() {
      return Strings.ff("%s@%s(flags:%Xh,info:%s)", this.getName(), this.getLocation(), this.getFlags(), this.getInformation());
   }
}
