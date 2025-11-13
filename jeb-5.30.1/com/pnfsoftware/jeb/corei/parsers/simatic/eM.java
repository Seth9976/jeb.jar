package com.pnfsoftware.jeb.corei.parsers.simatic;

import com.pnfsoftware.jeb.core.units.code.asm.type.IAliasType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.code.simatic.IS7Block;
import com.pnfsoftware.jeb.core.units.code.simatic.S7;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class eM {
   public static final String q = "BYTE";
   public static final String RF = "CHAR";
   public static final String xK = "WORD";
   public static final String Dw = "DWORD";
   public static final String Uv = "INT";
   public static final String oW = "DINT";
   public static final String gO = "REAL";
   public static final String nf = "S5TIME";
   public static final String gP = "TIME";
   public static final String za = "DATE";
   public static final String lm = "TIME_OF_DAY";
   public static final String zz = "DATE_AND_TIME";
   private static final String KT = "STRING";
   public static final String JY = "POINTER";
   public static final String HF = "ANY";
   public static final String LK = "BLOCK_FB";
   public static final String io = "BLOCK_FC";
   public static final String qa = "BLOCK_DB";
   public static final String Hk = "BLOCK_SDB";
   public static final String Me = "COUNTER";
   public static final String PV = "TIMER";
   public static final String oQ = "S7TIME";
   public static final String xW = "STRING_FIXED8";
   private ITypeManager Gf;
   private INativeType Ef;
   private INativeType cC;
   private INativeType sH;
   private INativeType CE;
   private INativeType wF;
   private INativeType If;
   private INativeType Dz;
   private INativeType mI;
   private INativeType jq;
   private INativeType ui;
   private INativeType TX;
   private INativeType Rr;
   private INativeType EB;
   private INativeType Xo;
   private INativeType Bu;
   private INativeType IN;
   private INativeType rL;
   private INativeType eJ;
   private INativeType YN;
   private INativeType Rv;
   private INativeType zx;
   private INativeType ZT;
   private Map Ri = new HashMap();
   private int GY;

   public eM(ITypeManager var1) {
      this.Gf = var1;
      this.q();
   }

   public void q() {
      this.Ef = this.q("BYTE", "unsigned __int8");
      this.cC = this.q("CHAR", "__int8");
      this.sH = this.q("WORD", "unsigned __int16");
      this.CE = this.q("DWORD", "unsigned __int32");
      this.wF = this.q("INT", "__int16");
      this.If = this.q("DINT", "__int32");
      this.Dz = this.q("REAL", "float");
      this.mI = this.q("S5TIME", "unsigned __int16");
      this.jq = this.q("TIME", "unsigned __int32");
      this.ui = this.q("DATE", "unsigned __int16");
      this.TX = this.q("TIME_OF_DAY", "unsigned __int16");
      this.Rr = this.q("DATE_AND_TIME", "unsigned __int64");
      this.EB = this.q("BLOCK_FB", "unsigned __int16");
      this.Xo = this.q("BLOCK_FC", "unsigned __int16");
      this.Bu = this.q("BLOCK_DB", "unsigned __int16");
      this.IN = this.q("BLOCK_SDB", "unsigned __int16");
      this.rL = this.q("COUNTER", "unsigned __int16");
      this.eJ = this.q("TIMER", "unsigned __int16");
      this.YN = this.Gf.getType("POINTER");
      if (this.YN == null) {
         this.YN = this.Gf.createAlias("POINTER", this.Gf.createArray(this.Ef, 6));
      }

      Assert.a(this.YN != null);
      this.Rv = this.Gf.getType("ANY");
      if (this.Rv == null) {
         this.Rv = this.Gf.createAlias("ANY", this.Gf.createArray(this.Ef, 10));
      }

      Assert.a(this.YN != null);
      this.zx = this.Gf.getType("S7TIME");
      if (this.zx == null) {
         this.zx = this.Gf.createAlias("S7TIME", this.Gf.createArray("BYTE", 6));
      }

      this.ZT = this.Gf.getType("STRING_FIXED8");
      if (this.ZT == null) {
         this.ZT = this.Gf.createAlias("STRING_FIXED8", this.Gf.createArray("CHAR", 8));
      }

      this.RF();
   }

   private INativeType q(String var1, String var2) {
      Object var3 = this.Gf.getType(var1);
      if (var3 == null) {
         var3 = this.Gf.createAlias(var1, var2);
      }

      Assert.a(var3 != null);
      return (INativeType)var3;
   }

   public IPrototypeItem q(kY var1, IS7Block var2, List var3) {
      if (var2.getType().isAnyOf(S7.BlockType.FB, S7.BlockType.SFB)) {
         return this.Dw(var1, var2, var3);
      } else if (var2.getType().isAnyOf(S7.BlockType.FC, S7.BlockType.SFC)) {
         return this.RF(var1, var2, var3);
      } else if (var2.getType() == S7.BlockType.OB) {
         return this.xK(var1, var2, var3);
      } else {
         throw new RuntimeException("Cannot generate prototype for block " + var2);
      }
   }

   private IPrototypeItem xK(kY var1, IS7Block var2, List var3) {
      ICallingConvention var4 = this.Gf.getCallingConventionManager().getConvention("__FC_CC");
      return this.Gf.createPrototype(var4, null, Arrays.asList(), null);
   }

   private IPrototypeItem Dw(kY var1, IS7Block var2, List var3) {
      IStructureType var4 = this.q(var1, var2);
      ICallingConvention var5 = this.Gf.getCallingConventionManager().getConvention("__FB_CC");
      IPrototypeItem var6 = this.Gf.createPrototype(var5, null, Arrays.asList(var4.getReference(), this.CE), null);
      if (var3 != null) {
         var3.add("blkPointer");
         var3.add("blkOffset");
      }

      return var6;
   }

   public IStructureType q(kY var1, IS7Block var2) {
      String var3 = var2.getName();
      String var4 = "_DATA_" + var3;
      if (this.Gf.getType(var4) != null) {
         return (IStructureType)this.Gf.getType(var4);
      } else {
         IStructureType var5 = this.Gf.createStructureOrUnion(var4, 2, 2);
         kY.eo var6 = var1.q(S7.SectionType.IN);
         if (var6 != null) {
            INativeType var7 = this.q(var6, "_IN_" + var3);
            this.Gf.addStructureField(var5, "in", var7);
         }

         var6 = var1.q(S7.SectionType.RET);
         if (var6 != null) {
            INativeType var12 = this.q(var6, "_RET_" + var3);
            this.Gf.addStructureField(var5, "ret", var12);
         }

         var6 = var1.q(S7.SectionType.OUT);
         if (var6 != null) {
            INativeType var13 = this.q(var6, "_OUT_" + var3);
            this.Gf.addStructureField(var5, "out", var13);
         }

         var6 = var1.q(S7.SectionType.IN_OUT);
         if (var6 != null) {
            INativeType var14 = this.q(var6, "_IN_OUT_" + var3);
            this.Gf.addStructureField(var5, "in_out", var14);
         }

         var6 = var1.q(S7.SectionType.STAT);
         if (var6 != null) {
            INativeType var15 = this.q(var6, "_STAT_" + var3);
            this.Gf.addStructureField(var5, "stat", var15);
         }

         return var5;
      }
   }

   public INativeType q(kY.eo var1, String var2) {
      this.q();
      return this.q(var1, var2, 0);
   }

   IPrototypeItem RF(kY var1, IS7Block var2, List var3) {
      Assert.a(var2.getType().isAnyOf(S7.BlockType.FC, S7.BlockType.SFC));
      String var4 = "_PROTO_" + var2.getName();
      if (this.Gf.getType(var4) != null) {
         return (IPrototypeItem)((IAliasType)this.Gf.getType(var4)).getAliasedType();
      } else {
         ArrayList var5 = new ArrayList();
         Object var6 = null;
         kY.eo var7 = var1.q(S7.SectionType.IN);
         if (var7 != null) {
            this.q(var7, "in", var5, var3);
         }

         var7 = var1.q(S7.SectionType.RET);
         if (var7 != null) {
            this.q(var7, "ret", var5, var3);
         }

         var7 = var1.q(S7.SectionType.OUT);
         if (var7 != null) {
            this.q(var7, "out", var5, var3);
         }

         var7 = var1.q(S7.SectionType.IN_OUT);
         if (var7 != null) {
            this.q(var7, "in_out", var5, var3);
         }

         ICallingConvention var8 = this.Gf.getCallingConventionManager().getConvention("__FC_CC");
         IPrototypeItem var9 = this.Gf.createPrototype(var8, (INativeType)var6, var5, null);
         this.Gf.createAlias(var4, var9);
         return var9;
      }
   }

   private void q(kY.eo var1, String var2, List var3, List var4) {
      int var5 = var3.size();
      int var6 = 0;

      for (kY.nI var8 : var1.RF()) {
         S7.DataType var10 = var8.q();

         Object var9 = switch (var10) {
            case BOOL, BYTE, CHAR, WORD, INT, DWORD, DINT, REAL, DATE, TIME_OF_DAY, TIME, S5TIME -> {
               INativeType var19 = this.q(var10);
               yield var19.getReference();
            }
            case TIMER, COUNTER, BLOCK_FB, BLOCK_FC, BLOCK_DB, BLOCK_SDB -> {
               INativeType var18 = this.q(var10);
               yield var18;
            }
            case DATE_AND_TIME, ARRAY, STRING, STRUCT, POINTER, ANY -> {
               Couple var11 = this.q(var8, "cplx" + var5, 1);
               INativeType var12 = (INativeType)var11.getFirst();
               String var13 = var12.getSignature().replace('[', '`').replace(']', '\'');
               if (var10 == S7.DataType.POINTER || var10 == S7.DataType.ANY) {
                  var13 = "UNKNOWN";
               }

               IAliasType var14 = (IAliasType)this.Gf.getType("MC7P_" + var13);
               if (var14 == null) {
                  var14 = this.Gf.createAlias("MC7P_" + var13, "void*");
               }

               Assert.a(var14.getBitsize() == 32);
               String var16;
               if (var10 != S7.DataType.ANY) {
                  var16 = "MC7PTR_";
                  IStructureType var15 = (IStructureType)this.Gf.getType(var16 + var13);
                  if (var15 == null) {
                     var15 = this.Gf.createStructureOrUnion(var16 + var13, 1, 1);
                     this.Gf.addStructureField(var15, "dbnum", this.sH);
                     this.Gf.addStructureField(var15, "dataptr", var14);
                  }

                  Assert.a(var15.getBitsize() == 48);
               } else {
                  var16 = "MC7ANY_";
                  IStructureType var20 = (IStructureType)this.Gf.getType(var16 + var13);
                  if (var20 == null) {
                     var20 = this.Gf.createStructureOrUnion(var16 + var13, 1, 1);
                     this.Gf.addStructureField(var20, "magic", this.Ef);
                     this.Gf.addStructureField(var20, "datatype", this.Ef);
                     this.Gf.addStructureField(var20, "cnt", this.sH);
                     this.Gf.addStructureField(var20, "dbnum", this.sH);
                     this.Gf.addStructureField(var20, "dataptr", var14);
                  }

                  Assert.a(var20.getBitsize() == 80);
               }

               IAliasType var17 = (IAliasType)this.Gf.getType("MC7P_" + var16 + var13);
               if (var17 == null) {
                  var17 = this.Gf.createAlias("MC7P_" + var16 + var13, "void*");
               }

               Assert.a(var17.getBitsize() == 32);
               yield var17;
            }
            default -> throw new RuntimeException("TBI: needed to generate FC proto: " + var10);
         };

         var3.add(var9);
         if (var4 != null) {
            var4.add(var2 + var6);
         }

         var5++;
         var6++;
      }
   }

   private INativeType q(kY.eo var1, String var2, int var3) {
      IStructureType var4 = this.Gf.createStructureOrUnion(var2, 1, 2);

      for (kY.nI var6 : var1.RF()) {
         Couple var7 = this.q(var6, var2, var3);
         INativeType var8 = (INativeType)var7.getFirst();
         int var9 = (Integer)var7.getSecond();
         int var10 = var6.RF();
         int var11 = var10 / 8;
         String var12 = "fld_" + var11 + "_" + var10 % 8;
         this.Gf.addStructureField(var4, var12, var8, var11, var9, 0, 0);
      }

      return var4;
   }

   private Couple q(kY.nI var1, String var2, int var3) {
      byte var5 = 0;
      S7.DataType var6 = var1.q();
      Object var4;
      switch (var6) {
         case ARRAY:
            S7.DataType var14 = var1.HF();
            int[] var8 = var1.lm();
            int var9 = var8[0];
            int var10 = 0;
            Object var11;
            if (var14 == S7.DataType.STRUCT) {
               var11 = this.q(var1.LK(), var2 + this.GY++, var3 + 1);
            } else if (var14 == S7.DataType.STRING) {
               int var12 = var1.Dw();
               var11 = this.q(var12);
               if (((INativeType)var11).getBitsize() % 16 != 0) {
                  IStructureType var13 = this.Gf.createStructure(null);
                  this.Gf.addStructureField(var13, "str", (INativeType)var11);
                  this.Gf.addStructureField(var13, "padding", this.Ef);
                  var11 = var13;
               }
            } else if (var14 == S7.DataType.BOOL) {
               int var15 = (var9 + 7) / 8;
               var11 = this.Gf.createArray(this.Ef, var15);
               var10 = 1;
            } else {
               var11 = this.q(var14);
            }

            for (var4 = var11; var10 < var8.length; var10++) {
               int var16 = var8[var10];
               var4 = this.Gf.createArray((INativeType)var4, var16);
            }
            break;
         case STRING:
            int var7 = var1.Dw();
            var4 = this.q(var7);
            break;
         case STRUCT:
            var4 = this.q(var1.LK(), var2 + this.GY++, var3 + 1);
            break;
         case POINTER:
         case ANY:
         default:
            var4 = this.q(var6);
            if (var6 == S7.DataType.BOOL) {
               var5 = 1;
            }
            break;
         case MULTI_INST_FB:
         case MULTI_INST_SFB:
            throw new RuntimeException("TBI: Native type generation for multi-instance data blocks");
      }

      return new Couple(var4, Integer.valueOf(var5));
   }

   private INativeType q(int var1) {
      Assert.a(var1 >= 0 && var1 <= 254);
      Object var2 = (INativeType)this.Ri.get(var1);
      if (var2 == null) {
         String var3 = "STRING";
         if (var1 != 254) {
            var3 = var3 + var1;
         }

         var2 = this.Gf.getType(var3);
         if (var2 != null) {
            Assert.a(var2 instanceof IStructureType);
            this.Ri.put(var1, var2);
         } else {
            IStructureType var4 = this.Gf.createStructure(var3);
            this.Gf.addStructureField(var4, "maxlen", this.Ef);
            this.Gf.addStructureField(var4, "len", this.Ef);
            this.Gf.addStructureField(var4, "data", this.Gf.createArray(this.cC, var1));
            var2 = var4;
            this.Ri.put(var1, var4);
         }
      }

      return (INativeType)var2;
   }

   private INativeType q(S7.DataType var1) {
      switch (var1) {
         case BOOL:
            return this.Ef;
         case BYTE:
            return this.Ef;
         case CHAR:
            return this.cC;
         case WORD:
            return this.sH;
         case INT:
            return this.wF;
         case DWORD:
            return this.CE;
         case DINT:
            return this.If;
         case REAL:
            return this.Dz;
         case DATE:
            return this.ui;
         case TIME_OF_DAY:
            return this.TX;
         case TIME:
            return this.jq;
         case S5TIME:
            return this.mI;
         case TIMER:
            return this.eJ;
         case COUNTER:
            return this.rL;
         case BLOCK_FB:
            return this.EB;
         case BLOCK_FC:
            return this.Xo;
         case BLOCK_DB:
            return this.Bu;
         case BLOCK_SDB:
            return this.IN;
         case DATE_AND_TIME:
            return this.Rr;
         case ARRAY:
         case STRING:
         case STRUCT:
         default:
            throw new RuntimeException("Cannot generate native type for S7 base type: " + var1);
         case POINTER:
            return this.YN;
         case ANY:
            return this.Rv;
      }
   }

   public INativeType RF() {
      INativeType var1 = this.Gf.getType("OB1_HEADER");
      if (var1 != null) {
         return var1;
      } else {
         IStructureType var2 = this.Gf.createStructure("OB1_HEADER");
         this.Gf.addStructureField(var2, "ev_class", this.Ef);
         this.Gf.addStructureField(var2, "scan_1", this.Ef);
         this.Gf.addStructureField(var2, "priority", this.Ef);
         this.Gf.addStructureField(var2, "ob_number", this.Ef);
         this.Gf.addStructureField(var2, "reserved1", this.Ef);
         this.Gf.addStructureField(var2, "reserved2", this.Ef);
         this.Gf.addStructureField(var2, "prev_cycle", this.wF);
         this.Gf.addStructureField(var2, "min_cycle", this.wF);
         this.Gf.addStructureField(var2, "max_cycle", this.wF);
         this.Gf.addStructureField(var2, "date_time", this.Rr);
         return var2;
      }
   }
}
