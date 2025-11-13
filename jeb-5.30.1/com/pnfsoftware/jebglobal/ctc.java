package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.FlowInformation;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.InstructionFlags;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.math.MathUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jeb.util.serialization.objects.SerEnumSet;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Ser
public class ctc implements IInstruction {
   private static final ILogger qa = GlobalLog.getLogger(ctc.class);
   public static final int q = 1;
   public static final int RF = 2;
   public static final int xK = 4;
   public static final int Dw = 3;
   public static final int Uv = 7;
   public static final int oW = 64;
   @SerId(1)
   int gO;
   @SerId(2)
   byte[] nf;
   @SerId(3)
   private int Hk;
   @SerId(4)
   int gP;
   @SerId(5)
   int[] za;
   @SerId(6)
   int lm;
   @SerId(7)
   int zz;
   @SerId(8)
   int JY;
   @SerId(9)
   cqj[] HF;
   @SerId(10)
   Set LK;
   @SerTransient
   ctb io;

   @SerCustomInitPostGraph
   private void JY() {
      this.io = (ctb)ctb.q.get(this.Hk);
   }

   void q(cqj[] var1, int var2) {
      if (var2 == var1.length) {
         this.HF = var1;
      } else {
         this.HF = (cqj[])Arrays.copyOf((Object[])var1, var2);
      }
   }

   ctc(int var1, byte[] var2, ctb var3) {
      this.gO = var1;
      this.nf = var2;
      this.io = var3;
      this.Hk = var3.RF;
   }

   ctc(ctc var1) {
      this.gO = var1.gO;
      this.nf = var1.nf;
      this.Hk = var1.Hk;
      this.gP = var1.gP;
      this.za = var1.za;
      this.lm = var1.lm;
      this.zz = var1.zz;
      this.JY = var1.JY;
      this.HF = var1.HF;
      this.io = var1.io;
   }

   @Override
   public int getProcessorMode() {
      return this.gO;
   }

   @Override
   public int getSize() {
      return this.nf.length;
   }

   @Override
   public byte[] getCode() {
      return this.nf;
   }

   public int q() {
      return this.Hk;
   }

   public int RF() {
      return this.zz;
   }

   public int xK() {
      return this.JY;
   }

   @Override
   public String getPrefix() {
      int var1 = this.za[0];
      if (var1 == 0) {
         return null;
      } else if (var1 == 240) {
         return "lock";
      } else if (var1 == 243) {
         switch (this.io.RF) {
            case 108:
            case 109:
            case 110:
            case 111:
            case 164:
            case 165:
            case 170:
            case 171:
            case 172:
            case 173:
               return "rep";
            case 166:
            case 167:
            case 174:
            case 175:
               return "repe";
            default:
               return null;
         }
      } else if (var1 == 242) {
         switch (this.io.RF) {
            case 108:
            case 109:
            case 110:
            case 111:
            case 164:
            case 165:
            case 170:
            case 171:
            case 172:
            case 173:
               return "rep";
            case 166:
            case 167:
            case 174:
            case 175:
               return "repne";
            default:
               return null;
         }
      } else {
         return null;
      }
   }

   @Override
   public String getMnemonic() {
      String var1 = this.io.Dw;
      if ((this.Uv() & 1) != 0) {
         if ((this.io.xK & 256L) != 0L) {
            switch (this.io.RF) {
               case 6688622:
               case 8392558:
                  int var9 = this.HF[1].getOperandBitsize();
                  if (var9 == 32) {
                     var1 = "movd";
                  } else {
                     if (var9 != 64) {
                        throw new RuntimeException();
                     }

                     var1 = "movq";
                  }
                  break;
               case 6688638:
               case 8392574:
                  int var8 = this.HF[0].getOperandBitsize();
                  if (var8 == 32) {
                     var1 = "movd";
                  } else {
                     if (var8 != 64) {
                        throw new RuntimeException();
                     }

                     var1 = "movq";
                  }
               case 6688706:
               case 8392642:
               case 15863746:
               case 15929282:
                  break;
               case 8392567:
                  if (this.nf.length == 3 && this.nf[0] == -59 && this.nf[2] == 119) {
                     if (this.nf[1] == -8) {
                        var1 = "zeroupper";
                        break;
                     }

                     if (this.nf[1] == -4) {
                        var1 = "zeroall";
                        break;
                     }
                  }

                  if (Licensing.isDebugBuild()) {
                     throw new RuntimeException();
                  }
                  break;
               default:
                  if (Licensing.isDebugBuild()) {
                     throw new RuntimeException(Strings.ff("Polymorphic rendering for VEX: 0x%X", this.io.RF));
                  }
            }
         }

         return (this.io.xK & 8388608L) != 0L ? var1 : "v" + var1;
      } else if ((this.io.xK & 256L) == 0L) {
         return var1;
      } else {
         String var2 = null;
         int var3 = this.za();
         int var4 = this.lm();
         switch (this.io.RF) {
            case -217113080:
            case -217075992:
            case 997622:
            case 1712273653:
               if ((this.lm & 72) == 72) {
                  var2 = var1 + "q";
               } else {
                  var2 = var1 + "d";
               }
               break;
            case 96:
               if (var3 == 16) {
                  var2 = "pusha";
               }
               break;
            case 97:
               if (var3 == 16) {
                  var2 = "popa";
               }
               break;
            case 108:
            case 110:
            case 164:
            case 166:
            case 170:
            case 172:
            case 174:
               var2 = this.io.Dw + "b";
               break;
            case 109:
            case 111:
            case 165:
            case 167:
            case 171:
            case 173:
            case 175:
               String var11 = "";

               var2 = this.io.Dw + switch (var3) {
                  case 16 -> "w";
                  case 32 -> {
                     if (this.io.RF == 109 || this.io.RF == 111) {
                        yield "d";
                     }
                  }
                  case 64 -> "q";
                  default -> "";
               };
               break;
            case 144:
               if (this.za[0] == 243) {
                  var2 = "pause";
               }
               break;
            case 152:
               if (var3 == 16) {
                  var2 = "cbw";
               } else if (var3 == 64) {
                  var2 = "cdqe";
               }
               break;
            case 153:
               if (var3 == 16) {
                  var2 = "cwd";
               } else if (var3 == 64) {
                  var2 = "cqo";
               }
               break;
            case 156:
               if (var3 == 16) {
                  var2 = "pushf";
               } else if (this.gO == 64) {
                  var2 = "pushfq";
               }
               break;
            case 157:
               if (var3 == 16) {
                  var2 = "popf";
               } else if (this.gO == 64) {
                  var2 = "popfq";
               }
               break;
            case 207:
               if (var3 == 32) {
                  var2 = "iretd";
               } else if (var3 == 64) {
                  var2 = "iretq";
               }
               break;
            case 227:
               if (var4 == 16) {
                  var2 = "jcxz";
               } else if (var4 == 64) {
                  var2 = "jrcxz";
               }
               break;
            case 1027584:
            case 1027592:
               if (var3 == 64) {
                  var2 = var1 + "64";
               }
               break;
            case 6688622:
            case 6688638:
            case 8392558:
            case 8392574:
               int var10 = this.io.RF != 8392558 && this.io.RF != 6688622 ? 0 : 1;
               int var12 = this.HF[var10].getOperandBitsize();
               if (var12 == 32) {
                  var2 = "movd";
               } else {
                  if (var12 != 64) {
                     throw new RuntimeException();
                  }

                  var2 = "movq";
               }
               break;
            case 6688706:
            case 8392642:
            case 15863746:
            case 15929282:
               if (this.HF.length >= 3) {
                  cqj var5 = this.HF[this.HF.length - 1];

                  String var6 = switch ((int)var5.getOperandValue()) {
                     case 0 -> "eq";
                     case 1 -> "lt";
                     case 2 -> "le";
                     case 3 -> "unord";
                     case 4 -> "neq";
                     case 5 -> "nlt";
                     case 6 -> "nle";
                     case 7 -> "ord";
                     default -> "";
                  };
                  String var7 = (this.Uv() & 1) != 0 ? "vcmp" : "cmp";
                  var2 = Strings.ff("%s%s%s", var7, var6, this.io.Dw.substring(3));
               }
            case 8392567:
               break;
            default:
               if (Licensing.isDebugBuild()) {
                  throw new RuntimeException("custom getMnemonic TBI");
               }

               return var1;
         }

         return var2 == null ? var1 : var2;
      }
   }

   public cqj[] Dw() {
      if ((this.io.xK & 4194304L) == 0L) {
         return this.HF;
      } else {
         cqj[] var1 = null;
         switch (this.io.RF) {
            case 6688706:
            case 8392642:
            case 15863746:
            case 15929282:
               if (this.HF.length >= 3) {
                  cqj var2 = this.HF[this.HF.length - 1];
                  if (var2.getOperandValue() >= 0L && var2.getOperandValue() <= 7L) {
                     int var3 = this.HF.length - 1;
                     var1 = new cqj[var3];
                     System.arraycopy(this.HF, 0, var1, 0, var3);
                  }
               }

               return var1 == null ? this.HF : var1;
            default:
               throw new RuntimeException();
         }
      }
   }

   public int Uv() {
      return this.gP & 65535;
   }

   public boolean oW() {
      return (this.gP & 1) != 0;
   }

   public boolean gO() {
      return (this.gP & 2) != 0;
   }

   public boolean nf() {
      return (this.gP & 4) != 0;
   }

   public int gP() {
      return this.gP >> 3 & 7;
   }

   public int za() {
      return this.gP >> 16 & 0xFF;
   }

   public int lm() {
      return this.gP >> 24 & 0xFF;
   }

   public long q(int var1) {
      if (this.io.RF == 215) {
         return this.RF(3);
      } else if (var1 >= 0 && var1 < this.HF.length) {
         cqj var2 = this.HF[var1];
         if (!var2.q()) {
            return 0L;
         } else {
            byte var3 = -1;
            switch (this.io.RF) {
               case 108:
               case 109:
               case 164:
               case 165:
               case 170:
               case 171:
                  if (var1 == 0) {
                     var3 = 0;
                  }
                  break;
               case 166:
               case 167:
               case 174:
               case 175:
                  if (var1 == 1) {
                     var3 = 0;
                  }
            }

            if (var3 >= 0) {
               return ctf.q(var3, 2, 16);
            } else {
               byte var4 = 3;
               if (var2 instanceof cte && ((cte)var2).getOperandType() == 4) {
                  long var9 = ((cte)var2).getOperandValue();
                  int var11 = ctf.q(var9);
                  if (var11 == 4 || var11 == 5) {
                     var4 = 2;
                  }
               } else if (var2 instanceof ctd) {
                  long var5 = ((ctd)var2).getMemoryBaseRegister();
                  int var7 = ctf.q(var5);
                  if (var7 == 4 || var7 == 5) {
                     var4 = 2;
                  }

                  var5 = ((ctd)var2).getMemoryIndexRegister();
                  var7 = ctf.q(var5);
                  if (var7 == 4 || var7 == 5) {
                     var4 = 2;
                  }
               }

               return this.RF(var4);
            }
         }
      } else {
         return 0L;
      }
   }

   private long RF(int var1) {
      return ctf.q(switch (this.za[1]) {
         case 38 -> 0;
         case 46 -> 1;
         case 54 -> 2;
         case 62 -> 3;
         case 100 -> 4;
         case 101 -> 5;
         default -> var1;
      }, 2, 16);
   }

   @Override
   public IFlowInformation getBreakingFlow(long var1) {
      boolean var3 = (this.io.xK & 16384L) != 0L;
      if (!var3) {
         return FlowInformation.NONE;
      } else {
         FlowInformation var4 = new FlowInformation();
         switch (this.io.RF) {
            case 112:
            case 113:
            case 114:
            case 115:
            case 116:
            case 117:
            case 118:
            case 119:
            case 120:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            case 126:
            case 127:
            case 224:
            case 225:
            case 226:
            case 227:
            case 3968:
            case 3969:
            case 3970:
            case 3971:
            case 3972:
            case 3973:
            case 3974:
            case 3975:
            case 3976:
            case 3977:
            case 3978:
            case 3979:
            case 3980:
            case 3981:
            case 3982:
            case 3983:
               var4.addTarget(new CodePointer(var1 + this.nf.length, this.gO));
            case 233:
            case 235:
               cte var5 = (cte)this.HF[0];
               var4.addTarget(new CodePointer(var1 + var5.getOperandValue(), this.gO));
            case 194:
            case 195:
            case 202:
            case 203:
            case 204:
            case 205:
            case 206:
            case 207:
            case 234:
            case 241:
            case 244:
            case 65312:
            case 65320:
               break;
            default:
               qa.warn(S.L("Branching instruction, target was not determined"));
         }

         return var4;
      }
   }

   @Override
   public IFlowInformation getRoutineCall(long var1) {
      boolean var3 = (this.io.xK & 32768L) != 0L;
      if (!var3) {
         return FlowInformation.NONE;
      } else {
         FlowInformation var4 = new FlowInformation();
         switch (this.io.RF) {
            case 154:
            case 65296:
            case 65304:
               break;
            case 232:
               cte var5 = (cte)this.HF[0];
               var4.addTarget(new CodePointer(var1 + var5.getOperandValue(), this.gO));
               break;
            default:
               qa.warn(S.L("Sub-routine instruction, target was not determined"));
         }

         return var4;
      }
   }

   @Override
   public IFlowInformation collectIndirectCallReferences(long var1) {
      boolean var3 = (this.io.xK & 32768L) != 0L;
      if (!var3) {
         return FlowInformation.NONE;
      } else {
         FlowInformation var4 = new FlowInformation();
         switch (this.io.RF) {
            case 154:
               return FlowInformation.NONE;
            case 232:
               return FlowInformation.NONE;
            case 65296:
            case 65304:
               if (this.HF[0] instanceof ctd var5) {
                  if (var5.getMemoryBaseRegister() == -1L && var5.getMemoryIndexRegister() == -1L) {
                     var4.addTarget(new CodePointer(var5.getMemoryDisplacement() & MathUtil.makeMask(this.gO), this.gO));
                  }

                  if (this.gO == 64 && var5.getMemoryIndexRegister() == -1L && ctf.RF(var5.getMemoryBaseRegister()) == 10 && var5.getMemoryDisplacement() != 0L
                     )
                   {
                     long var6 = var5.getMemoryDisplacement() + var1 + this.getSize();
                     var4.addTarget(new CodePointer(var6, this.gO));
                  }
               } else if (this.HF[0] instanceof cte var8 && var8.getOperandType() == 5) {
                  var4.addTarget(new CodePointer(var8.getOperandValue(var1), this.gO));
               }
               break;
            default:
               qa.warn(S.L("Sub-routine instruction, target was not determined"));
         }

         return var4;
      }
   }

   @Override
   public void getDefUse(List var1, List var2, Object var3) {
   }

   public Set zz() {
      if (this.LK == null) {
         switch (this.io.RF) {
            case 204:
            case 205:
            case 206:
            case 207:
            case 241:
            case 244:
               this.LK = SerEnumSet.wrap(InstructionFlags.class, EnumSet.of(InstructionFlags.INTERRUPT_EXEC));
               break;
            default:
               this.LK = Collections.emptySet();
         }
      }

      return this.LK;
   }

   @Override
   public boolean canThrow() {
      return false;
   }

   @Override
   public String format(Object var1) {
      long var2 = var1 instanceof Long ? (Long)var1 : 0L;
      StringBuilder var4 = new StringBuilder();
      String var5 = this.getPrefix();
      if (var5 != null) {
         var4.append(var5);
         var4.append(' ');
      }

      var4.append(this.getMnemonic());
      if (this.HF != null && this.HF.length > 0) {
         var4.append(" ");
         int var6 = 0;

         for (cqj var10 : this.HF) {
            if (var6 >= 1) {
               var4.append(", ");
            }

            var4.append(var10.format(this, var2));
            var6++;
         }
      }

      return var4.toString();
   }

   @Override
   public String toString() {
      return this.format(null);
   }

   static {
      crj.q = true;
   }
}
