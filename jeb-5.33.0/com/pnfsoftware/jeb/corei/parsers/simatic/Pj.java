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

public class Pj {
   private ITypeManager pC;
   private INativeType A;
   private INativeType kS;
   private INativeType wS;
   private INativeType UT;
   private INativeType E;
   private INativeType sY;
   private INativeType ys;
   private INativeType ld;
   private INativeType gp;
   private INativeType oT;
   private INativeType fI;
   private INativeType WR;
   private INativeType NS;
   private INativeType vP;
   private INativeType xC;
   private INativeType ED;
   private INativeType Sc;
   private INativeType ah;
   private INativeType eP;
   private INativeType UO;
   private INativeType Ab;
   private INativeType rl;
   private Map z = new HashMap();
   private int Ek;

   public Pj(ITypeManager var1) {
      this.pC = var1;
      this.pC();
   }

   public void pC() {
      this.A = this.pC("BYTE", "unsigned __int8");
      this.kS = this.pC("CHAR", "__int8");
      this.wS = this.pC("WORD", "unsigned __int16");
      this.UT = this.pC("DWORD", "unsigned __int32");
      this.E = this.pC("INT", "__int16");
      this.sY = this.pC("DINT", "__int32");
      this.ys = this.pC("REAL", "float");
      this.ld = this.pC("S5TIME", "unsigned __int16");
      this.gp = this.pC("TIME", "unsigned __int32");
      this.oT = this.pC("DATE", "unsigned __int16");
      this.fI = this.pC("TIME_OF_DAY", "unsigned __int16");
      this.WR = this.pC("DATE_AND_TIME", "unsigned __int64");
      this.NS = this.pC("BLOCK_FB", "unsigned __int16");
      this.vP = this.pC("BLOCK_FC", "unsigned __int16");
      this.xC = this.pC("BLOCK_DB", "unsigned __int16");
      this.ED = this.pC("BLOCK_SDB", "unsigned __int16");
      this.Sc = this.pC("COUNTER", "unsigned __int16");
      this.ah = this.pC("TIMER", "unsigned __int16");
      this.eP = this.pC.getType("POINTER");
      if (this.eP == null) {
         this.eP = this.pC.createAlias("POINTER", this.pC.createArray(this.A, 6));
      }

      Assert.a(this.eP != null);
      this.UO = this.pC.getType("ANY");
      if (this.UO == null) {
         this.UO = this.pC.createAlias("ANY", this.pC.createArray(this.A, 10));
      }

      Assert.a(this.eP != null);
      this.Ab = this.pC.getType("S7TIME");
      if (this.Ab == null) {
         this.Ab = this.pC.createAlias("S7TIME", this.pC.createArray("BYTE", 6));
      }

      this.rl = this.pC.getType("STRING_FIXED8");
      if (this.rl == null) {
         this.rl = this.pC.createAlias("STRING_FIXED8", this.pC.createArray("CHAR", 8));
      }

      this.A();
   }

   private INativeType pC(String var1, String var2) {
      Object var3 = this.pC.getType(var1);
      if (var3 == null) {
         var3 = this.pC.createAlias(var1, var2);
      }

      Assert.a(var3 != null);
      return (INativeType)var3;
   }

   public IPrototypeItem pC(GK var1, IS7Block var2, List var3) {
      if (var2.getType().isAnyOf(S7.BlockType.FB, S7.BlockType.SFB)) {
         return this.wS(var1, var2, var3);
      } else if (var2.getType().isAnyOf(S7.BlockType.FC, S7.BlockType.SFC)) {
         return this.A(var1, var2, var3);
      } else if (var2.getType() == S7.BlockType.OB) {
         return this.kS(var1, var2, var3);
      } else {
         throw new RuntimeException("Cannot generate prototype for block " + var2);
      }
   }

   private IPrototypeItem kS(GK var1, IS7Block var2, List var3) {
      ICallingConvention var4 = this.pC.getCallingConventionManager().getConvention("__FC_CC");
      return this.pC.createPrototype(var4, null, Arrays.asList(), null);
   }

   private IPrototypeItem wS(GK var1, IS7Block var2, List var3) {
      IStructureType var4 = this.pC(var1, var2);
      ICallingConvention var5 = this.pC.getCallingConventionManager().getConvention("__FB_CC");
      IPrototypeItem var6 = this.pC.createPrototype(var5, null, Arrays.asList(var4.getReference(), this.UT), null);
      if (var3 != null) {
         var3.add("blkPointer");
         var3.add("blkOffset");
      }

      return var6;
   }

   public IStructureType pC(GK var1, IS7Block var2) {
      String var3 = var2.getName();
      String var4 = "_DATA_" + var3;
      if (this.pC.getType(var4) != null) {
         return (IStructureType)this.pC.getType(var4);
      } else {
         IStructureType var5 = this.pC.createStructureOrUnion(var4, 2, 2);
         GK.Av var6 = var1.pC(S7.SectionType.IN);
         if (var6 != null) {
            INativeType var7 = this.pC(var6, "_IN_" + var3);
            this.pC.addStructureField(var5, "in", var7);
         }

         var6 = var1.pC(S7.SectionType.RET);
         if (var6 != null) {
            INativeType var12 = this.pC(var6, "_RET_" + var3);
            this.pC.addStructureField(var5, "ret", var12);
         }

         var6 = var1.pC(S7.SectionType.OUT);
         if (var6 != null) {
            INativeType var13 = this.pC(var6, "_OUT_" + var3);
            this.pC.addStructureField(var5, "out", var13);
         }

         var6 = var1.pC(S7.SectionType.IN_OUT);
         if (var6 != null) {
            INativeType var14 = this.pC(var6, "_IN_OUT_" + var3);
            this.pC.addStructureField(var5, "in_out", var14);
         }

         var6 = var1.pC(S7.SectionType.STAT);
         if (var6 != null) {
            INativeType var15 = this.pC(var6, "_STAT_" + var3);
            this.pC.addStructureField(var5, "stat", var15);
         }

         return var5;
      }
   }

   public INativeType pC(GK.Av var1, String var2) {
      this.pC();
      return this.pC(var1, var2, 0);
   }

   IPrototypeItem A(GK var1, IS7Block var2, List var3) {
      Assert.a(var2.getType().isAnyOf(S7.BlockType.FC, S7.BlockType.SFC));
      String var4 = "_PROTO_" + var2.getName();
      if (this.pC.getType(var4) != null) {
         return (IPrototypeItem)((IAliasType)this.pC.getType(var4)).getAliasedType();
      } else {
         ArrayList var5 = new ArrayList();
         Object var6 = null;
         GK.Av var7 = var1.pC(S7.SectionType.IN);
         if (var7 != null) {
            this.pC(var7, "in", var5, var3);
         }

         var7 = var1.pC(S7.SectionType.RET);
         if (var7 != null) {
            this.pC(var7, "ret", var5, var3);
         }

         var7 = var1.pC(S7.SectionType.OUT);
         if (var7 != null) {
            this.pC(var7, "out", var5, var3);
         }

         var7 = var1.pC(S7.SectionType.IN_OUT);
         if (var7 != null) {
            this.pC(var7, "in_out", var5, var3);
         }

         ICallingConvention var8 = this.pC.getCallingConventionManager().getConvention("__FC_CC");
         IPrototypeItem var9 = this.pC.createPrototype(var8, (INativeType)var6, var5, null);
         this.pC.createAlias(var4, var9);
         return var9;
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private void pC(GK.Av var1, String var2, List var3, List var4) {
      int var5 = var3.size();
      int var6 = 0;

      for (GK.K var8 : var1.A()) {
         S7.DataType var10 = var8.pC();

         Object var9 = switch (var10) {
            case BOOL, BYTE, CHAR, WORD, INT, DWORD, DINT, REAL, DATE, TIME_OF_DAY, TIME, S5TIME -> {
               INativeType var19 = this.pC(var10);
               yield var19.getReference();
            }
            case TIMER, COUNTER, BLOCK_FB, BLOCK_FC, BLOCK_DB, BLOCK_SDB -> {
               INativeType var18 = this.pC(var10);
               yield var18;
            }
            case DATE_AND_TIME, ARRAY, STRING, STRUCT, POINTER, ANY -> {
               Couple var11 = this.pC(var8, "cplx" + var5, 1);
               INativeType var12 = (INativeType)var11.getFirst();
               String var13 = var12.getSignature().replace('[', '`').replace(']', '\'');
               if (var10 == S7.DataType.POINTER || var10 == S7.DataType.ANY) {
                  var13 = "UNKNOWN";
               }

               IAliasType var14 = (IAliasType)this.pC.getType("MC7P_" + var13);
               if (var14 == null) {
                  var14 = this.pC.createAlias("MC7P_" + var13, "void*");
               }

               Assert.a(var14.getBitsize() == 32);
               String var16;
               if (var10 != S7.DataType.ANY) {
                  var16 = "MC7PTR_";
                  IStructureType var15 = (IStructureType)this.pC.getType(var16 + var13);
                  if (var15 == null) {
                     var15 = this.pC.createStructureOrUnion(var16 + var13, 1, 1);
                     this.pC.addStructureField(var15, "dbnum", this.wS);
                     this.pC.addStructureField(var15, "dataptr", var14);
                  }

                  Assert.a(var15.getBitsize() == 48);
               } else {
                  var16 = "MC7ANY_";
                  IStructureType var20 = (IStructureType)this.pC.getType(var16 + var13);
                  if (var20 == null) {
                     var20 = this.pC.createStructureOrUnion(var16 + var13, 1, 1);
                     this.pC.addStructureField(var20, "magic", this.A);
                     this.pC.addStructureField(var20, "datatype", this.A);
                     this.pC.addStructureField(var20, "cnt", this.wS);
                     this.pC.addStructureField(var20, "dbnum", this.wS);
                     this.pC.addStructureField(var20, "dataptr", var14);
                  }

                  Assert.a(var20.getBitsize() == 80);
               }

               IAliasType var17 = (IAliasType)this.pC.getType("MC7P_" + var16 + var13);
               if (var17 == null) {
                  var17 = this.pC.createAlias("MC7P_" + var16 + var13, "void*");
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

   private INativeType pC(GK.Av var1, String var2, int var3) {
      IStructureType var4 = this.pC.createStructureOrUnion(var2, 1, 2);

      for (GK.K var6 : var1.A()) {
         Couple var7 = this.pC(var6, var2, var3);
         INativeType var8 = (INativeType)var7.getFirst();
         int var9 = (Integer)var7.getSecond();
         int var10 = var6.A();
         int var11 = var10 / 8;
         String var12 = "fld_" + var11 + "_" + var10 % 8;
         this.pC.addStructureField(var4, var12, var8, var11, var9, 0, 0);
      }

      return var4;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private Couple pC(GK.K var1, String var2, int var3) {
      byte var5 = 0;
      S7.DataType var6 = var1.pC();
      Object var4;
      switch (var6) {
         case ARRAY:
            S7.DataType var14 = var1.oT();
            int[] var8 = var1.ys();
            int var9 = var8[0];
            int var10 = 0;
            Object var11;
            if (var14 == S7.DataType.STRUCT) {
               var11 = this.pC(var1.fI(), var2 + this.Ek++, var3 + 1);
            } else if (var14 == S7.DataType.STRING) {
               int var12 = var1.wS();
               var11 = this.pC(var12);
               if (((INativeType)var11).getBitsize() % 16 != 0) {
                  IStructureType var13 = this.pC.createStructure(null);
                  this.pC.addStructureField(var13, "str", (INativeType)var11);
                  this.pC.addStructureField(var13, "padding", this.A);
                  var11 = var13;
               }
            } else if (var14 == S7.DataType.BOOL) {
               int var15 = (var9 + 7) / 8;
               var11 = this.pC.createArray(this.A, var15);
               var10 = 1;
            } else {
               var11 = this.pC(var14);
            }

            for (var4 = var11; var10 < var8.length; var10++) {
               int var16 = var8[var10];
               var4 = this.pC.createArray((INativeType)var4, var16);
            }
            break;
         case STRING:
            int var7 = var1.wS();
            var4 = this.pC(var7);
            break;
         case STRUCT:
            var4 = this.pC(var1.fI(), var2 + this.Ek++, var3 + 1);
            break;
         case POINTER:
         case ANY:
         default:
            var4 = this.pC(var6);
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

   private INativeType pC(int var1) {
      Assert.a(var1 >= 0 && var1 <= 254);
      Object var2 = (INativeType)this.z.get(var1);
      if (var2 == null) {
         String var3 = "STRING";
         if (var1 != 254) {
            var3 = var3 + var1;
         }

         var2 = this.pC.getType(var3);
         if (var2 != null) {
            Assert.a(var2 instanceof IStructureType);
            this.z.put(var1, var2);
         } else {
            IStructureType var4 = this.pC.createStructure(var3);
            this.pC.addStructureField(var4, "maxlen", this.A);
            this.pC.addStructureField(var4, "len", this.A);
            this.pC.addStructureField(var4, "data", this.pC.createArray(this.kS, var1));
            var2 = var4;
            this.z.put(var1, var4);
         }
      }

      return (INativeType)var2;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private INativeType pC(S7.DataType var1) {
      switch (var1) {
         case BOOL:
            return this.A;
         case BYTE:
            return this.A;
         case CHAR:
            return this.kS;
         case WORD:
            return this.wS;
         case INT:
            return this.E;
         case DWORD:
            return this.UT;
         case DINT:
            return this.sY;
         case REAL:
            return this.ys;
         case DATE:
            return this.oT;
         case TIME_OF_DAY:
            return this.fI;
         case TIME:
            return this.gp;
         case S5TIME:
            return this.ld;
         case TIMER:
            return this.ah;
         case COUNTER:
            return this.Sc;
         case BLOCK_FB:
            return this.NS;
         case BLOCK_FC:
            return this.vP;
         case BLOCK_DB:
            return this.xC;
         case BLOCK_SDB:
            return this.ED;
         case DATE_AND_TIME:
            return this.WR;
         case ARRAY:
         case STRING:
         case STRUCT:
         default:
            throw new RuntimeException("Cannot generate native type for S7 base type: " + var1);
         case POINTER:
            return this.eP;
         case ANY:
            return this.UO;
      }
   }

   public INativeType A() {
      INativeType var1 = this.pC.getType("OB1_HEADER");
      if (var1 != null) {
         return var1;
      } else {
         IStructureType var2 = this.pC.createStructure("OB1_HEADER");
         this.pC.addStructureField(var2, "ev_class", this.A);
         this.pC.addStructureField(var2, "scan_1", this.A);
         this.pC.addStructureField(var2, "priority", this.A);
         this.pC.addStructureField(var2, "ob_number", this.A);
         this.pC.addStructureField(var2, "reserved1", this.A);
         this.pC.addStructureField(var2, "reserved2", this.A);
         this.pC.addStructureField(var2, "prev_cycle", this.E);
         this.pC.addStructureField(var2, "min_cycle", this.E);
         this.pC.addStructureField(var2, "max_cycle", this.E);
         this.pC.addStructureField(var2, "date_time", this.WR);
         return var2;
      }
   }
}
