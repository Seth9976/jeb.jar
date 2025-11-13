package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import com.pnfsoftware.jeb.core.units.code.asm.processor.AbstractInstructionOperandList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IInstructionOperandGeneric;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.HashMap;
import java.util.Map;

@Ser
public class Yg extends AbstractInstructionOperandList {
   private static final Yg[] A = new Yg[0];
   @SerId(1)
   Yg.Av pC;
   private static Map kS;
   private static Map wS;

   public static Yg pC(int var0, long var1) {
      return pC(var0, var1, 65536);
   }

   public static Yg pC(long var0, int var2) {
      return pC(32, var0, var2);
   }

   public static Yg pC(int var0, Yg.Sv var1, long var2, int var4) {
      if (var2 < 0L && var1 == Yg.Sv.kS) {
         var2 = -var2;
      } else {
         var4 |= var1 == Yg.Sv.kS ? 262144 : 0;
      }

      if (var1 == Yg.Sv.A) {
         var4 |= 1048576;
      }

      return pC(var0, var2, var4);
   }

   private static Yg pC(int var0, long var1, int var3) {
      var3 &= 16777215;
      if (var0 <= 32) {
         var0 = 32;
      } else if (var0 <= 64) {
         var0 = 64;
      }

      if (var3 != 65536) {
         return new Yg(1, var0, var1, var3);
      } else {
         Map var4;
         if (var0 == 32) {
            if (kS == null) {
               kS = new HashMap();
            }

            var4 = kS;
         } else {
            if (var0 != 64) {
               return new Yg(1, var0, var1, var3);
            }

            if (wS == null) {
               wS = new HashMap();
            }

            var4 = wS;
         }

         Yg var5 = (Yg)var4.get(var1);
         if (var5 == null) {
            var5 = new Yg(1, var0, var1, var3);
            var4.put(var1, var5);
         }

         return var5;
      }
   }

   public static Yg pC(int var0, int var1, double var2) {
      return new Wl(var0, var1, var2, "%1.6e");
   }

   protected Yg(int var1, long var2, IInstructionOperandGeneric[] var4, int var5) {
      super(7, var1, var2, var5, var4);
   }

   protected Yg(int var1, int var2, long var3, int var5) {
      super(var1, var2, var3, var5);
   }

   public boolean wS() {
      return (this.getFlags() & 262144) != 0;
   }

   @Override
   public String getRegisterName(long var1) {
      throw new IllegalArgumentException(this + " is not a Register Operand");
   }

   @Override
   public String getAlias(long var1) {
      return this.pC == null ? super.formatOperand(null, 0L).toString() : this.pC.pC((int)this.value);
   }

   @Override
   protected CharSequence formatOperand(IInstruction var1, long var2) {
      return (CharSequence)(this.type != 1 || (this.value >= 10L || this.value <= -10L) && (this.getFlags() & 524288) == 0
         ? super.formatOperand(var1, var2)
         : Long.toString(this.value));
   }

   @Override
   public String getPrefix(IInstruction var1) {
      StringBuilder var2 = new StringBuilder(super.getPrefix(var1));
      if ((this.getFlags() & 65536) != 0) {
         var2.append('#');
      }

      if (this.wS()) {
         var2.append("-");
      }

      return var2.toString();
   }

   @Override
   public String getSuffix(IInstruction var1) {
      String var2 = super.getSuffix(var1);
      return (this.getFlags() & 131072) != 0 ? var2 + "!" : var2;
   }

   public boolean UT() {
      return (this.getFlags() & 131072) != 0;
   }

   public Long pC(long var1, int var3, IMachineContext var4) {
      switch (this.getOperandType()) {
         case 0:
            if (this.A(var3)) {
               return var1 + cv.pC(var3);
            }

            return PM.pC(var4, this.getOperandValue(), var3);
         case 1:
         case 2:
         case 3:
            if (this.wS()) {
               return -this.getOperandValue(var1);
            }

            return this.getOperandValue(var1);
         default:
            return null;
      }
   }

   public boolean A(int var1) {
      if (this.getOperandType() == 7 && this.E().length == 1) {
         Yg var2 = this.E()[0];
         return var2.A(var1);
      } else {
         return false;
      }
   }

   public boolean kS(int var1) {
      if (this.getOperandType() == 7 && this.E().length == 1) {
         Yg var2 = this.E()[0];
         return var2.kS(var1);
      } else {
         return false;
      }
   }

   public boolean wS(int var1) {
      if (this.getOperandType() == 7 && this.E().length == 1) {
         Yg var2 = this.E()[0];
         return var2.wS(var1);
      } else {
         return false;
      }
   }

   public boolean UT(int var1) {
      return false;
   }

   public boolean E(int var1) {
      if (this.getOperandType() == 7 && this.E().length == 1) {
         Yg var2 = this.E()[0];
         return var2.E(var1);
      } else {
         return false;
      }
   }

   public Yg[] E() {
      IInstructionOperand[] var1 = super.getOperands();
      return var1.length > 0 ? (Yg[])var1 : A;
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      return 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!super.equals(var1)) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         Yg var2 = (Yg)var1;
         return this.pC == var2.pC;
      }
   }

   @Ser
   public static enum Av {
      pC(
         new String[]{
            "#0",
            "#1",
            "#2",
            "#3",
            "#4",
            "#5",
            "#6",
            "#7",
            "#8",
            "#9",
            "#10",
            "#11",
            "#12",
            "#13",
            "#14",
            "#15",
            "M32_User",
            "M32_FIQ",
            "M32_IRQ",
            "M32_Svc",
            "10100",
            "10101",
            "M32_Monitor",
            "M32_Abort",
            "11000",
            "11001",
            "M32_Hyp",
            "M32_Undef",
            "11100",
            "11101",
            "11110",
            "M32_System"
         }
      ),
      A(new String[]{"p0", "p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8", "p9", "p10", "p11", "p12", "p13", "p14", "p15"}),
      kS(new String[]{"LE", "BE"}),
      wS(new String[]{"NONE", "f", "i", "if", "a", "af", "ai", "aif"}),
      UT(new String[]{"EQ", "NE", "CS", "CC", "MI", "PL", "VS", "VC", "HI", "LS", "GE", "LT", "GT", "LE", "AL", "NV"}),
      E(new String[]{"LSL", "LSR", "ASR", "ROR", "RRX", "MSL", "UXTB", "UXTH", "UXTW", "UXTX", "SXTB", "SXTH", "SXTW", "SXTX"}),
      sY(new String[]{"#0x0", "OSHLD", "OSHST", "OSH", "#0x4", "NSHLD", "NSHST", "NSH", "#0x8", "ISHLD", "ISHST", "ISH", "#0xc", "LD", "ST", "SY"}),
      ys(new String[]{"#0x0", "#0x1", "#0x2", "#0x3", "#0x4", "#0x5", "#0x6", "#0x7", "#0x8", "#0x9", "#0xa", "#0xb", "#0xc", "#0xd", "#0xe", "SY"}),
      ld(
         new String[]{
            "PLDL1KEEP",
            "PLDL1STRM",
            "PLDL2KEEP",
            "PLDL2STRM",
            "PLDL3KEEP",
            "PLDL3STRM",
            "#6",
            "#7",
            "PLIL1KEEP",
            "PLIL1STRM",
            "PLIL2KEEP",
            "PLIL2STRM",
            "PLIL3KEEP",
            "PLIL3STRM",
            "#14",
            "#15",
            "PSTL1KEEP",
            "PSTL1STRM",
            "PSTL2KEEP",
            "PSTL2STRM",
            "PSTL3KEEP",
            "PSTL3STRM",
            "#22",
            "#23",
            "#24",
            "#25",
            "#26",
            "#27",
            "#28",
            "#29",
            "#30",
            "#31"
         }
      ),
      gp(
         new String[]{
            "S1E1R",
            "S1E1W",
            "S1E0R",
            "S1E0W",
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            "S1E2R",
            "S1E2W",
            null,
            null,
            "S12E1R",
            "S12E1W",
            "S12E0R",
            "S12E0W",
            "S1E3R",
            "S1E3W",
            null,
            null,
            null,
            null,
            null,
            null
         }
      ),
      oT(
         new String[]{
            null,
            null,
            null,
            null,
            "IVAC",
            "ISW",
            null,
            null,
            null,
            "CSW",
            null,
            null,
            null,
            "CISW",
            null,
            null,
            null,
            null,
            null,
            null,
            "ZVA",
            null,
            null,
            null,
            "CVAC",
            null,
            "CVAU",
            null,
            "CIVAC",
            null,
            null,
            null
         }
      ),
      fI(new String[]{"IALLUIS", "IALLU", null, "IVAU"}),
      WR(
         new String[]{
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            "VMALLE1IS",
            "VAE1IS",
            "ASIDE1IS",
            "VAAE1IS",
            null,
            "VALE1IS",
            null,
            "VAALE1IS",
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            "VMALLE1",
            "VAE1",
            "ASIDE1",
            "VAAE1",
            null,
            "VALE1",
            null,
            "VAALE1",
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            "IPAS2E1IS",
            null,
            null,
            null,
            "IPAS2LE1IS",
            null,
            null,
            "ALLE2IS",
            "VAE2IS",
            null,
            null,
            "ALLE1IS",
            "VALE2IS",
            "VMALLS12E1IS",
            null,
            null,
            "IPAS2E1",
            null,
            null,
            null,
            "IPAS2LE1",
            null,
            null,
            "ALLE2",
            "VAE2",
            null,
            null,
            "ALLE1",
            "VALE2",
            "VMALLS12E1",
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            "ALLE3IS",
            "VAE3IS",
            null,
            null,
            null,
            "VALE3IS",
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            "ALLE3",
            "VAE3",
            null,
            null,
            null,
            "VALE3",
            null,
            null
         }
      ),
      NS(new String[]{null, "SPSel", "DAIFSet", "DAIFClr"}),
      vP(new String[]{""});

      private final String[] xC;

      private Av(String[] var3) {
         this.xC = var3;
      }

      public String pC(int var1) {
         return this.xC[var1];
      }
   }

   @Ser
   public static enum Sv {
      pC,
      A,
      kS;
   }
}
