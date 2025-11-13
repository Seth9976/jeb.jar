package com.pnfsoftware.jeb.core.units.code.debug.impl;

import com.pnfsoftware.jeb.core.units.UnitAddress;
import com.pnfsoftware.jeb.core.units.code.debug.DebuggerEventType;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerEventData;
import com.pnfsoftware.jeb.core.units.code.debug.ITypedValue;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;

public class DebuggerEventData implements IDebuggerEventData {
   DebuggerEventType type;
   long threadId;
   String address;
   UnitAddress ua;
   int signal;
   ITypedValue retval;
   byte[] output;

   public DebuggerEventData(DebuggerEventType var1, long var2, String var4) {
      if (var1 == null) {
         throw new NullPointerException("Debugger event type cannot be null");
      } else {
         this.type = var1;
         this.threadId = var2;
         this.address = var4;
      }
   }

   @Override
   public DebuggerEventType getType() {
      return this.type;
   }

   @Override
   public long getThreadId() {
      return this.threadId;
   }

   @Override
   public String getAddress() {
      return this.address;
   }

   @Override
   public UnitAddress getUnitAddress() {
      return this.ua;
   }

   public void setUnitAddress(UnitAddress var1) {
      this.ua = var1;
   }

   public int getSignalNumber() {
      return this.signal;
   }

   public void setSignalNumber(int var1) {
      this.signal = var1;
   }

   @Override
   public ITypedValue getReturnValue() {
      return this.retval;
   }

   public void setReturnValue(ITypedValue var1) {
      this.retval = var1;
   }

   @Override
   public byte[] getOutput() {
      return this.output;
   }

   public void setOutput(byte[] var1) {
      this.output = var1;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      Strings.ff(var1, "type:%s", this.getType());
      if (this.getThreadId() != 0L) {
         Strings.ff(var1, ",tid:%d", this.getThreadId());
      }

      if (this.getAddress() != null) {
         Strings.ff(var1, "@%s", this.getAddress());
      }

      if (this.getSignalNumber() != 0) {
         Strings.ff(var1, ",sig=%d", this.getSignalNumber());
      }

      if (this.getReturnValue() != null) {
         Strings.ff(var1, ",retval=%s", this.getReturnValue());
      }

      if (this.getOutput() != null) {
         Strings.ff(var1, ",output='%s'", Formatter.escapeBytes(this.getOutput()));
      }

      return var1.toString();
   }
}
