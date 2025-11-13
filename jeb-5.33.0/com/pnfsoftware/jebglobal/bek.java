package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.exceptions.NotImplementedException;
import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.DefUseInfo;
import com.pnfsoftware.jeb.core.units.code.FlowInformation;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.InstructionFlags;
import com.pnfsoftware.jeb.core.units.code.android.IDexUnit;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDalvikInstruction;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerVersion;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Ser
@SerVersion(1)
public final class bek implements IDalvikInstruction {
   private static final ILogger kS = GlobalLog.getLogger(bek.class);
   @SerId(1)
   int pC;
   @SerId(value = 8, version = 2)
   bej A;
   @SerId(value = 2, version = 0, deprecated = true)
   @Deprecated
   private byte[] wS;
   @SerId(value = 4, version = 0, deprecated = true)
   @Deprecated
   private bem[] UT;
   @SerId(value = 5, version = 0, deprecated = true)
   @Deprecated
   private bey E;
   @SerId(value = 6, version = 0, deprecated = true)
   @Deprecated
   private beh sY;
   @SerId(value = 7, version = 1, deprecated = true)
   @Deprecated
   private Boolean ys;

   @SerCustomInitPostGraph
   private void E() {
      if (this.A == null) {
         this.A = new bej();
         this.A.A = this.wS;
         this.wS = null;
         this.A.kS = this.UT;
         this.UT = null;
         this.A.wS = this.E;
         this.E = null;
         this.A.UT = this.sY;
         this.sY = null;
         this.A.E = this.ys;
         this.ys = null;
         this.A.pC();
      }
   }

   @Override
   public int getProcessorMode() {
      return 0;
   }

   @Override
   public String getPrefix() {
      return null;
   }

   public bel.Av pC() {
      return this.A.pC;
   }

   @Override
   public String getMnemonic() {
      String var1 = this.A.pC.kS;
      if (Strings.isBlank(var1)) {
         var1 = Strings.ff("invalid-opcode-%X", this.A.pC.pC);
      }

      return var1;
   }

   @Override
   public int getOpcode() {
      return this.A.pC.pC;
   }

   @Override
   public long getOffset() {
      return this.pC;
   }

   public void pC(int var1) {
      this.pC += var1;
   }

   @Override
   public long getOffsetEnd() {
      return (long)this.pC + this.getSize();
   }

   @Override
   public int getSize() {
      return this.A.A.length;
   }

   @Override
   public byte[] getCode() {
      return this.A.A;
   }

   @Override
   public IInstructionOperand[] getOperands() {
      return this.A.kS;
   }

   public bem[] A() {
      return this.A.kS;
   }

   @Override
   public int getParameterCount() {
      return this.A.kS.length;
   }

   public bem A(int var1) {
      return this.A.kS[var1];
   }

   @Override
   public int getParameterType(int var1) {
      return this.A.kS[var1].getType();
   }

   @Override
   public long getParameterValue(int var1) {
      return this.A.kS[var1].getValue();
   }

   public bey kS() {
      return this.A.wS == null ? null : this.A.wS;
   }

   @Override
   public boolean isSwitch() {
      return this.A.wS != null;
   }

   @Override
   public boolean isArray() {
      return this.A.UT != null;
   }

   @Override
   public boolean isPseudoInstruction() {
      return this.A.wS != null || this.A.UT != null;
   }

   public bey.Av[] wS() {
      return this.A.wS == null ? null : this.A.wS.A;
   }

   public beh UT() {
      return this.A.UT == null ? null : this.A.UT;
   }

   @Override
   public boolean isOptimized() {
      return this.A.sY != 0;
   }

   @Override
   public IFlowInformation getBreakingFlow(long var1) {
      if (var1 != this.pC) {
         throw new JebRuntimeException(Strings.ff("Expected instruction address %Xh, got %Xh", this.pC, var1));
      } else {
         return this.getBreakingFlow();
      }
   }

   @Override
   public IFlowInformation getBreakingFlow() {
      FlowInformation var1 = new FlowInformation();
      if ((this.A.pC.UT & 64) != 0) {
         switch (this.A.pC.pC) {
            case 115:
               return var1;
            default:
               return FlowInformation.NONE;
         }
      } else if ((this.A.pC.UT & 16) != 0) {
         switch (this.A.pC.pC) {
            case 241:
               return var1;
            default:
               return FlowInformation.NONE;
         }
      } else {
         switch (this.A.pC.pC) {
            case 14:
            case 15:
            case 16:
            case 17:
            case 39:
               return var1;
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            default:
               return FlowInformation.NONE;
            case 40:
            case 41:
            case 42:
               var1.addTarget(new CodePointer((long)this.pC + 2 * (int)this.A.kS[0].getValue()));
               return var1;
            case 43:
            case 44:
               var1.addTarget(new CodePointer((long)this.pC + this.A.A.length));

               for (bey.Av var5 : this.A.wS.A) {
                  var1.addTarget(new CodePointer((long)this.pC + 2 * var5.A));
               }

               return var1;
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
               var1.addTarget(new CodePointer((long)this.pC + this.A.A.length));
               var1.addTarget(new CodePointer((long)this.pC + 2 * (int)this.A.kS[this.A.kS.length - 1].getValue()));
               return var1;
         }
      }
   }

   @Override
   public IFlowInformation getRoutineCall(long var1) {
      return FlowInformation.NONE;
   }

   @Override
   public IFlowInformation getRoutineCall() {
      return FlowInformation.NONE;
   }

   @Override
   public IFlowInformation collectIndirectCallReferences(long var1) {
      return FlowInformation.NONE;
   }

   @Override
   public IFlowInformation collectIndirectCallReferences() {
      return FlowInformation.NONE;
   }

   @Override
   public long getPrimaryBranchAddress(long var1) {
      if (var1 != this.pC) {
         throw new JebRuntimeException(Strings.ff("Expected instruction address %Xh, got %Xh", this.pC, var1));
      } else {
         return IDalvikInstruction.super.getPrimaryBranchAddress(var1);
      }
   }

   @Override
   public long getPrimaryBranchAddress() {
      return this.getPrimaryBranchAddress(this.pC);
   }

   @Override
   public Collection getInstructionFlags() {
      switch (this.A.pC.pC) {
         case 14:
         case 15:
         case 16:
         case 17:
         case 39:
            return Arrays.asList(InstructionFlags.ROUTINE_TERMINATOR);
         default:
            return Collections.emptySet();
      }
   }

   @Override
   public boolean canThrow() {
      switch (this.A.pC.pC) {
         case 26:
         case 27:
         case 28:
         case 29:
         case 30:
         case 31:
         case 32:
         case 33:
         case 34:
         case 35:
         case 36:
         case 37:
         case 38:
         case 39:
         case 68:
         case 69:
         case 70:
         case 71:
         case 72:
         case 73:
         case 74:
         case 75:
         case 76:
         case 77:
         case 78:
         case 79:
         case 80:
         case 81:
         case 82:
         case 83:
         case 84:
         case 85:
         case 86:
         case 87:
         case 88:
         case 89:
         case 90:
         case 91:
         case 92:
         case 93:
         case 94:
         case 95:
         case 96:
         case 97:
         case 98:
         case 99:
         case 100:
         case 101:
         case 102:
         case 103:
         case 104:
         case 105:
         case 106:
         case 107:
         case 108:
         case 109:
         case 110:
         case 111:
         case 112:
         case 113:
         case 114:
         case 116:
         case 117:
         case 118:
         case 119:
         case 120:
         case 147:
         case 148:
         case 158:
         case 159:
         case 179:
         case 180:
         case 190:
         case 191:
         case 211:
         case 212:
         case 219:
         case 220:
         case 250:
         case 251:
         case 252:
         case 253:
         case 254:
         case 255:
         case 1791:
         case 5375:
         case 8959:
            return true;
         default:
            return false;
      }
   }

   @Override
   public void getDefUse(List var1, List var2, Object var3) {
      int var4 = this.A.pC.wS;
      if (var4 < 0) {
         throw new JebRuntimeException(Strings.ff("Undefined DefUse for instruction %s (0x%02X)", this.A.pC.kS, this.A.pC.pC));
      } else {
         var1.clear();
         var2.clear();
         if ((var4 & 65536) != 0) {
            if (this.A.pC.A.length() >= 2 && this.A.pC.A.charAt(1) == '5') {
               for (int var12 = 1; var12 < this.A.kS.length; var12++) {
                  int var17 = (int)this.A.kS[var12].getValue();
                  if (!var2.contains(var17)) {
                     var2.add(var17);
                  }
               }
            } else {
               if (this.A.pC.A.length() < 2 || this.A.pC.A.charAt(1) != 'r') {
                  throw new JebRuntimeException(Strings.ff("Manual handling not implemented for instruction %s (0x%02X)", this.A.pC.kS, this.A.pC.pC));
               }

               int var11 = (int)(this.A.kS[1].getValue() & -1L);
               int var16 = (int)(this.A.kS[1].getValue() >> 32 & -1L);

               for (int var21 = var11; var21 <= var16; var21++) {
                  if (!var2.contains(var21)) {
                     var2.add(var21);
                  }
               }
            }
         } else {
            int var5 = var4 >> 4 & 15;

            for (int var6 = 0; var5 != 0; var6++) {
               if ((var5 & 1) != 0) {
                  int var7 = (int)this.A.kS[var6].getValue();
                  if (!var1.contains(var7)) {
                     var1.add(var7);
                  }
               }

               var5 >>= 1;
            }

            var5 = var4 & 15;

            for (int var13 = 0; var5 != 0; var13++) {
               if ((var5 & 1) != 0) {
                  int var18 = (int)this.A.kS[var13].getValue();
                  if (!var2.contains(var18)) {
                     var2.add(var18);
                  }
               }

               var5 >>= 1;
            }

            var5 = var4 >> 12 & 15;

            for (int var14 = 0; var5 != 0; var14++) {
               if ((var5 & 1) != 0) {
                  int var19 = (int)this.A.kS[var14].getValue();
                  if (!var1.contains(var19)) {
                     var1.add(var19);
                  }

                  if (!var1.contains(var19 + 1)) {
                     var1.add(var19 + 1);
                  }
               }

               var5 >>= 1;
            }

            var5 = var4 >> 8 & 15;

            for (int var15 = 0; var5 != 0; var15++) {
               if ((var5 & 1) != 0) {
                  int var20 = (int)this.A.kS[var15].getValue();
                  if (!var2.contains(var20)) {
                     var2.add(var20);
                  }

                  if (!var2.contains(var20 + 1)) {
                     var2.add(var20 + 1);
                  }
               }

               var5 >>= 1;
            }
         }
      }
   }

   @Override
   public DefUseInfo getDefUseInfo(long var1, int var3) throws NotImplementedException {
      DefUseInfo var4 = new DefUseInfo(var3);
      var4.insnAddress = var1;
      ArrayList var5 = new ArrayList();
      ArrayList var6 = new ArrayList();
      this.getDefUse(var5, var6, null);

      for (int var8 : var5) {
         var4.def.add(var8, 32);
      }

      for (int var10 : var6) {
         var4.use.add(var10, 32);
      }

      return var4;
   }

   @Override
   public String format(Object var1) {
      if (this.A == null) {
         return "(unparsed)";
      } else {
         StringBuilder var2 = new StringBuilder(this.A.pC.kS);
         var2.append(' ');
         IDexUnit var3 = null;
         if (var1 instanceof IDexUnit) {
            var3 = (IDexUnit)var1;
         }

         int var4 = 0;

         for (bem var8 : this.A.kS) {
            if (var4 >= 1) {
               var2.append(", ");
            }

            int var9 = var8.getType();
            long var10 = var8.getValue();
            switch (var9) {
               case 0:
                  Strings.ff(var2, "v%d", var8.getValue());
                  break;
               case 1:
                  Strings.ff(var2, "#%X", var8.getValue());
                  break;
               case 2:
                  int var15 = (int)var10;
                  int var16 = this.A.pC.UT & 15;
                  var2.append(pC(var3, var16, var15));
                  break;
               case 3:
                  int var14 = this.pC + (int)var10 * 2;
                  Strings.ff(var2, "%X", var14);
                  break;
               case 4:
                  Strings.ff(var2, "v%d-v%d", var10 & 65535L, var10 >> 32 & 65535L);
                  break;
               case 5:
                  int var12 = (int)var10;
                  int var13 = this.A.pC.UT >> 8 & 15;
                  var2.append(pC(var3, var13, var12));
                  break;
               default:
                  throw new JebRuntimeException("Unsupported parameter type: " + var9);
            }

            var4++;
         }

         return var2.toString();
      }
   }

   @Override
   public int getParameterFirstIndexType() {
      return kS(this.A.pC.UT & 15);
   }

   @Override
   public int getParameterSecondIndexType() {
      return kS(this.A.pC.UT >> 8 & 15);
   }

   private static int kS(int var0) {
      switch (var0) {
         case 1:
            return 16;
         case 2:
            return 17;
         case 3:
            return 18;
         case 4:
            return 19;
         case 5:
            return 20;
         case 6:
            return 21;
         case 7:
            return 33;
         case 8:
            return 34;
         case 9:
            return 32;
         case 10:
            return 22;
         default:
            return 0;
      }
   }

   private static String wS(int var0) {
      switch (var0) {
         case 1:
            return "string";
         case 2:
            return "type";
         case 3:
            return "field";
         case 4:
            return "method";
         case 5:
            return "proto";
         case 6:
            return "call_site";
         case 7:
            return "vtaboff";
         case 8:
            return "fieldoff";
         case 9:
            return "inline";
         case 10:
            return "method_handle";
         default:
            return "unknown";
      }
   }

   private static String pC(IDexUnit var0, int var1, int var2) {
      if (var0 != null) {
         try {
            switch (var1) {
               case 1:
                  return var0.getString(var2).getValue();
               case 2:
                  return var0.getType(var2).getSignature();
               case 3:
                  return var0.getField(var2).getSignature();
               case 4:
                  return var0.getMethod(var2).getSignature();
               case 5:
                  return var0.getPrototype(var2).generate(true);
               case 6:
                  return var0.getCallSite(var2).generate(true);
               case 7:
               case 8:
               case 9:
               default:
                  break;
               case 10:
                  return var0.getMethodHandle(var2).generate(true);
            }
         } catch (Exception var4) {
            JebCoreService.notifySilentExceptionToClient(new RuntimeException("Cannot generate object representation for " + var1 + "," + var2, var4));
         }
      }

      return wS(var1) + "@" + var2;
   }

   @Override
   public String toString() {
      return this.A == null ? "(unparsed)" : this.getMnemonic();
   }
}
