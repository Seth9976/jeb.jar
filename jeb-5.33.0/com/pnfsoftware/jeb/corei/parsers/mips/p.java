package com.pnfsoftware.jeb.corei.parsers.mips;

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
import com.pnfsoftware.jebglobal.cfi;
import com.pnfsoftware.jebglobal.cfj;
import java.util.Arrays;

@Ser
public class p extends AbstractProcessor implements ICodeResolver {
   private static final ILogger pC = GlobalLog.getLogger(p.class);
   @SerTransient
   private cfi A;
   @SerId(1)
   @Deprecated
   private Tb kS;
   @SerId(2)
   private boolean wS = true;
   @SerId(3)
   private Tb UT;
   @SerId(4)
   private uX E;

   @SerCustomInitPostGraph
   private void kS() {
      this.A = new cfi(this);
      if (this.E == null) {
         this.E = new uX(this.kS);
      }

      if (this.UT == null) {
         this.UT = this.E.pC();
      }
   }

   public p(int var1) {
      this(var1, null);
   }

   public p(int var1, IUnitCreator var2) {
      super(8, pC(var1, var2), var2, 4);
      this.supportedModes.add(32);
      this.supportedModes.add(64);
      if (var2 == null) {
         this.setEndianness(Endianness.BIG_ENDIAN);
      }

      this.E = uX.pC(var2);
      if (this.E != null && var2 instanceof IUnit) {
         StringBuilder var3 = new StringBuilder();
         if (var2 instanceof IELFUnit) {
            int var4 = ((IELFUnit)var2).getHeader().getFlags();
            boolean var5 = true;
            if (this.E.A()) {
               var5 = false;
               var3.append("Mips ASE:");
               if (this.E.kS()) {
                  var3.append(" microMips,");
               }

               if (this.E.wS()) {
                  var3.append(" Mips16 ISA,");
               }

               if (this.E.UT()) {
                  var3.append(" MDMX,");
               }

               var3.deleteCharAt(var3.length() - 1).append('\n');
            }

            if (this.E.E() != 0) {
               var5 = false;
               var3.append("Mips Machine Variant: ").append(this.E.sY());
            }

            if (!var5) {
               String var6 = S.L("Mips file is not fully supported due to additional features to be implemented in future versions of JEB:\n")
                  + var3.toString();
               pC.error(var6);
               var2.notifyListeners(new JebEvent(J.Notification, new ExceptionNotification(new JebRuntimeException(var6), var6, 1)));
               var2.notifyListeners(new JebEvent(J.Notification, new ClientNotification(var6)));
            }

            var3.insert(0, "MipsVersion: " + this.E.pC() + "\n");
            var3.insert(0, "e_flags: " + Integer.toHexString(var4) + "\n");
         } else {
            var3.append("MipsVersion: ").append(this.E.pC()).append('\n');
         }

         var3.insert(0, ((IUnit)var2).getNotes());
         ((IUnit)var2).setNotes(var3.toString());
      }

      if (this.E == null) {
         this.E = uX.pC(this.getMode());
      }

      this.pC(this.E);
      this.kS();
   }

   private static int pC(int var0, IUnitCreator var1) {
      uX var2 = uX.pC(var1);
      if (var2 != null && var2.pC().kS() != (var0 == 64)) {
         return var2.pC().kS() ? 64 : 32;
      } else {
         return var0;
      }
   }

   @Override
   public String getRegisterName(long var1) {
      return cfj.pC(var1, this.mode);
   }

   protected Sv pC(IMachineContext var1) throws ProcessorException {
      byte[] var2 = new byte[4];

      try {
         var1.getMemory().read(var1.getRegisters().getProgramCounter(), 4, var2, 0);
      } catch (MemoryException var4) {
         throw new ProcessorException(var4);
      }

      return this.pC(var2, 0, 4);
   }

   protected Sv pC(byte[] var1, int var2, int var3) throws ProcessorException {
      byte[] var4 = Arrays.copyOfRange(var1, var2, Math.min(var2 + 4, var3));
      switch (this.getMode()) {
         case 0:
         case 32:
         case 64:
         default:
            if (var4.length < 4) {
               throw new ProcessorException(Strings.ff("Invalid ARM instruction %s must be 32 bit instruction", Formatter.byteArrayToHex(var4)));
            } else {
               Sv var5 = (Sv)this.A.getInstruction(new BytesBlock(var4, this.getEndianness(), 4));
               if (var5.A().pC()) {
                  byte[] var6 = Arrays.copyOfRange(var1, var2 + 4, Math.min(var2 + 8, var3));
                  if (var6.length < 4) {
                     throw new ProcessorException(Strings.ff("Can not validate the delay slot instruction", Formatter.byteArrayToHex(var4)));
                  }

                  Sv var7 = (Sv)this.A.getInstruction(new BytesBlock(var6, this.getEndianness(), 4));
                  if (var7.wS()) {
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

   public ICodePointer pC(IMachineContext var1, Sv var2) throws ProcessorException {
      return var2.pC(var1);
   }

   public IResolvedOperandValue pC(IMachineContext var1, Sv var2, int var3) throws ProcessorException {
      return null;
   }

   public Tb pC() {
      return this.E == null ? null : this.E.pC();
   }

   public void pC(uX var1) {
      this.E = var1;

      try {
         switch (var1.pC()) {
            case sY:
            case ld:
            case oT:
               if (this.getMode() != 64) {
                  super.setMode(64);
               }
               break;
            case E:
            case ys:
            case gp:
               if (this.getMode() == 64) {
                  super.setMode(32);
               }
         }
      } catch (ProcessorException var3) {
         pC.catchingSilent(var3);
      }
   }

   @Override
   public int setMode(int var1) throws ProcessorException {
      var1 = super.setMode(var1);
      if (this.E == null) {
         return var1;
      } else {
         if (var1 == 64) {
            switch (this.E.pC()) {
               case E:
                  this.E = new uX(this.E, Tb.sY);
                  break;
               case ys:
                  this.E = new uX(this.E, Tb.ld);
                  break;
               case gp:
                  this.E = new uX(this.E, Tb.oT);
                  break;
               case pC:
               case A:
                  this.E = new uX(this.E, Tb.UT);
            }
         } else if (var1 == 32 || var1 == 16) {
            switch (this.E.pC()) {
               case sY:
                  this.E = new uX(this.E, Tb.E);
                  break;
               case ld:
                  this.E = new uX(this.E, Tb.ys);
                  break;
               case oT:
                  this.E = new uX(this.E, Tb.gp);
               case E:
               case ys:
               case gp:
               case pC:
               case A:
               default:
                  break;
               case UT:
                  if (this.UT != Tb.UT) {
                     this.E = new uX(this.E, this.UT);
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

   public boolean A() {
      return this.wS;
   }
}
