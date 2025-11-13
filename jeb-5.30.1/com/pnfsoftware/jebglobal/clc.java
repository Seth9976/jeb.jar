package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.IUnitCreator;
import com.pnfsoftware.jeb.core.events.ClientNotification;
import com.pnfsoftware.jeb.core.events.ExceptionNotification;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractProcessor;
import com.pnfsoftware.jeb.core.units.code.asm.processor.BytesBlock;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.simulator.ICodeResolver;
import com.pnfsoftware.jeb.core.units.code.asm.simulator.IResolvedOperandValue;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.Endianness;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.Arrays;

@Ser
public class clc extends AbstractProcessor implements ICodeResolver {
   private static final ILogger q = GlobalLog.getLogger(clc.class);
   @SerTransient
   private clu RF;
   @SerId(1)
   @Deprecated
   private clg xK;
   @SerId(2)
   private boolean Dw = true;
   @SerId(3)
   private clg Uv;
   @SerId(4)
   private clf oW;

   @SerCustomInitPostGraph
   private void xK() {
      this.RF = new clu(this);
      if (this.oW == null) {
         this.oW = new clf(this.xK);
      }

      if (this.Uv == null) {
         this.Uv = this.oW.q();
      }
   }

   public clc(int var1) {
      this(var1, null);
   }

   public clc(int var1, IUnitCreator var2) {
      super(8, q(var1, var2), var2, 4);
      this.supportedModes.add(32);
      this.supportedModes.add(64);
      if (var2 == null) {
         this.setEndianness(Endianness.BIG_ENDIAN);
      }

      this.oW = clf.q(var2);
      if (this.oW != null && var2 instanceof IUnit) {
         StringBuilder var3 = new StringBuilder();
         if (var2 instanceof IELFUnit) {
            int var4 = ((IELFUnit)var2).getHeader().getFlags();
            boolean var5 = true;
            if (this.oW.RF()) {
               var5 = false;
               var3.append("Mips ASE:");
               if (this.oW.xK()) {
                  var3.append(" microMips,");
               }

               if (this.oW.Dw()) {
                  var3.append(" Mips16 ISA,");
               }

               if (this.oW.Uv()) {
                  var3.append(" MDMX,");
               }

               var3.deleteCharAt(var3.length() - 1).append('\n');
            }

            if (this.oW.oW() != 0) {
               var5 = false;
               var3.append("Mips Machine Variant: ").append(this.oW.gO());
            }

            if (!var5) {
               String var6 = S.L("Mips file is not fully supported due to additional features to be implemented in future versions of JEB:\n")
                  + var3.toString();
               q.error(var6);
               var2.notifyListeners(new JebEvent(J.Notification, new ExceptionNotification(new JebRuntimeException(var6), var6, 1)));
               var2.notifyListeners(new JebEvent(J.Notification, new ClientNotification(var6)));
            }

            var3.insert(0, "MipsVersion: " + this.oW.q() + "\n");
            var3.insert(0, "e_flags: " + Integer.toHexString(var4) + "\n");
         } else {
            var3.append("MipsVersion: ").append(this.oW.q()).append('\n');
         }

         var3.insert(0, ((IUnit)var2).getNotes());
         ((IUnit)var2).setNotes(var3.toString());
      }

      if (this.oW == null) {
         this.oW = clf.q(this.getMode());
      }

      this.q(this.oW);
      this.xK();
   }

   private static int q(int var0, IUnitCreator var1) {
      clf var2 = clf.q(var1);
      if (var2 != null && var2.q().xK() != (var0 == 64)) {
         return var2.q().xK() ? 64 : 32;
      } else {
         return var0;
      }
   }

   @Override
   public String getRegisterName(long var1) {
      return clv.q(var1, this.mode);
   }

   protected cki q(IMachineContext var1) throws ProcessorException {
      byte[] var2 = new byte[4];

      try {
         var1.getMemory().read(var1.getRegisters().getProgramCounter(), 4, var2, 0);
      } catch (MemoryException var4) {
         throw new ProcessorException(var4);
      }

      return this.q(var2, 0, 4);
   }

   protected cki q(byte[] var1, int var2, int var3) throws ProcessorException {
      byte[] var4 = Arrays.copyOfRange(var1, var2, Math.min(var2 + 4, var3));
      switch (this.getMode()) {
         case 0:
         case 32:
         case 64:
         default:
            if (var4.length < 4) {
               throw new ProcessorException(Strings.ff("Invalid ARM instruction %s must be 32 bit instruction", Formatter.byteArrayToHex(var4)));
            } else {
               cki var5 = (cki)this.RF.getInstruction(new BytesBlock(var4, this.getEndianness(), 4));
               if (var5.RF().q()) {
                  byte[] var6 = Arrays.copyOfRange(var1, var2 + 4, Math.min(var2 + 8, var3));
                  if (var6.length < 4) {
                     throw new ProcessorException(Strings.ff("Can not validate the delay slot instruction", Formatter.byteArrayToHex(var4)));
                  }

                  cki var7 = (cki)this.RF.getInstruction(new BytesBlock(var6, this.getEndianness(), 4));
                  if (var7.Dw()) {
                     throw new ProcessorException(Strings.ff("CTI instruction %s is not managed in delay slot", var7));
                  }
               }

               return var5;
            }
         case 16:
            throw new ProcessorException("microMIPS is not managed in the current MIPS plugin");
      }
   }

   @Override
   public ICodeResolver getResolver() {
      return this;
   }

   public ICodePointer q(IMachineContext var1, cki var2) throws ProcessorException {
      return var2.q(var1);
   }

   public IResolvedOperandValue q(IMachineContext var1, cki var2, int var3) throws ProcessorException {
      return null;
   }

   private static void q(byte[] var0, String var1) throws ProcessorException {
      throw new ProcessorException(Strings.ff("Instruction %s is WIP. See section [%s]", Formatter.byteArrayToHex(var0), var1));
   }

   public clg q() {
      return this.oW == null ? null : this.oW.q();
   }

   public void q(clf var1) {
      this.oW = var1;

      try {
         switch (var1.q()) {
            case gO:
            case gP:
            case lm:
               if (this.getMode() != 64) {
                  super.setMode(64);
               }
               break;
            case oW:
            case nf:
            case za:
               if (this.getMode() == 64) {
                  super.setMode(32);
               }
         }
      } catch (ProcessorException var3) {
         q.catchingSilent(var3);
      }
   }

   @Override
   public int setMode(int var1) throws ProcessorException {
      var1 = super.setMode(var1);
      if (this.oW == null) {
         return var1;
      } else {
         if (var1 == 64) {
            switch (this.oW.q()) {
               case oW:
                  this.oW = new clf(this.oW, clg.gO);
                  break;
               case nf:
                  this.oW = new clf(this.oW, clg.gP);
                  break;
               case za:
                  this.oW = new clf(this.oW, clg.lm);
                  break;
               case q:
               case RF:
                  this.oW = new clf(this.oW, clg.Uv);
            }
         } else if (var1 == 32 || var1 == 16) {
            switch (this.oW.q()) {
               case gO:
                  this.oW = new clf(this.oW, clg.oW);
                  break;
               case gP:
                  this.oW = new clf(this.oW, clg.nf);
                  break;
               case lm:
                  this.oW = new clf(this.oW, clg.za);
               case oW:
               case nf:
               case za:
               case q:
               case RF:
               default:
                  break;
               case Uv:
                  if (this.Uv != clg.Uv) {
                     this.oW = new clf(this.oW, this.Uv);
                  }
            }
         }

         return var1;
      }
   }

   @Override
   public boolean isRISC() {
      return true;
   }

   @Override
   public ProcessorType getType() {
      switch (this.getMode()) {
         case 64:
            return ProcessorType.MIPS64;
         default:
            return ProcessorType.MIPS;
      }
   }

   public void q(boolean var1) {
      this.Dw = var1;
   }

   public boolean RF() {
      return this.Dw;
   }
}
