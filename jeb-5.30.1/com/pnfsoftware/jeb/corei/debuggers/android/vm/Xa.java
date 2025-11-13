package com.pnfsoftware.jeb.corei.debuggers.android.vm;

import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.InstructionCoordinates;
import com.pnfsoftware.jeb.core.units.UnitAddress;
import com.pnfsoftware.jeb.core.units.code.IDecompilerUnit;
import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.IDalvikDebuggerUnit;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexDebugVariable;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerThreadStackFrame;
import com.pnfsoftware.jeb.core.units.code.debug.IDebuggerVariable;
import com.pnfsoftware.jeb.core.units.code.debug.ITypedValue;
import com.pnfsoftware.jeb.core.units.code.debug.impl.ValueVoid;
import com.pnfsoftware.jeb.core.util.DecompilerHelper;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.FZ;
import com.pnfsoftware.jebglobal.Fx;
import com.pnfsoftware.jebglobal.Jz;
import com.pnfsoftware.jebglobal.NB;
import com.pnfsoftware.jebglobal.Pi;
import com.pnfsoftware.jebglobal.Ux;
import com.pnfsoftware.jebglobal.bip;
import com.pnfsoftware.jebglobal.bjp;
import com.pnfsoftware.jebglobal.ch;
import com.pnfsoftware.jebglobal.hf;
import com.pnfsoftware.jebglobal.zy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Xa implements IDebuggerThreadStackFrame {
   private static final ILogger lm = GlobalLog.getLogger(Xa.class);
   private boolean zz = false;
   CI q;
   kY RF;
   long xK;
   String Dw;
   int Uv;
   int oW;
   Map gO = new HashMap();
   int nf;
   int gP;
   Map za;

   public Xa(kY var1, long var2, String var4) {
      this.q = var1.q();
      this.RF = var1;
      this.xK = var2;
      this.Dw = var4;
      this.RF();
   }

   private void RF() {
      try {
         UnitAddress var1 = this.q.Dw(this.Dw);
         if (var1 != null && var1.getUnit() != null) {
            ICodeCoordinates var2 = ((com.pnfsoftware.jeb.corei.parsers.dex.bK)var1.getUnit()).xK().q(this.Dw);
            if (var2 instanceof InstructionCoordinates) {
               this.nf = ((InstructionCoordinates)var2).getMethodId();
               this.gP = ((InstructionCoordinates)var2).getOffset();
               bjp var3 = ((com.pnfsoftware.jeb.corei.parsers.dex.bK)var1.getUnit()).gO(this.nf);
               if (var3 != null && var3.RF() != null) {
                  bip var4 = var3.RF().q();
                  this.za = var3.getDebugVariablesMap();
                  this.Uv = var4.getRegisterCount();
                  this.oW = DexUtil.getMethodSlotCount(var3.Uv().getShorty(), var3.RF().isStatic());
               }
            }
         }
      } catch (Exception var5) {
         lm.error("Cannot retrieve frame size: %s", var5);
      }
   }

   public kY q() {
      return this.RF;
   }

   @Override
   public long getId() {
      return this.xK;
   }

   @Override
   public String getAddress() {
      if (!this.q.q(true, false)) {
         return this.Dw;
      } else if (!(this.q.oW() instanceof Ux var2)) {
         return null;
      } else {
         try {
            NB var3 = var2.zz().q(this.RF.id, 0, 1);
            if (var3.q.length <= 0) {
               return null;
            } else {
               Jz var4 = var3.q[0];
               if (var4.q == this.xK) {
                  this.Dw = this.q.q(var4.RF);
               }

               return this.Dw;
            }
         } catch (Fx | IOException var5) {
            lm.catching(var5);
            return null;
         }
      }
   }

   public LR q(int var1, String var2) {
      if (!this.q.q(true, false)) {
         return null;
      } else if (!(this.q.oW() instanceof Ux var4)) {
         return null;
      } else if (!this.RF.RF()) {
         return null;
      } else {
         try {
            hf var5 = new hf(var1, this.q(var2));
            ch var6 = var4.zz().q(this.RF.getId(), this.xK, var5);
            if (var6 == null) {
               return null;
            } else {
               ITypedValue var7 = this.q.q(var6);
               if (var7 == null) {
                  return null;
               } else {
                  String var8 = "v" + this.xK(var1);
                  return new LR(var8, var7, 0, this);
               }
            }
         } catch (Fx | zy | IOException var9) {
            lm.catching(var9);
            return null;
         }
      }
   }

   public boolean q(String var1, String var2) {
      int var3 = this.RF(var1);
      if (var3 < 0) {
         return false;
      } else {
         byte var4 = this.q(var2);
         this.gO.put(var3, var4);
         return true;
      }
   }

   public LR RF(String var1, String var2) {
      int var3 = this.RF(var1);
      if (var3 < 0) {
         return null;
      } else {
         return !Strings.isBlank(var2) ? this.q(var3, var2) : this.q(this.xK(var3));
      }
   }

   @Override
   public List getVariables() {
      return this.getVariables(false);
   }

   @Override
   public List getVariables(boolean var1) {
      if (var1 && this.za != null) {
         ArrayList var2 = new ArrayList();
         var2.add(-1);

         for (IDexDebugVariable var5 : (List)this.za.getOrDefault(this.gP, Collections.emptyList())) {
            var2.add(var5.getRegister());
         }

         return this.q(var2);
      } else {
         return this.q(null);
      }
   }

   public LR q(int var1) {
      ArrayList var2 = new ArrayList();
      var2.add(var1);
      List var3 = this.q(var2);
      return (LR)Lists.getFirst(var3);
   }

   public List q(List var1) {
      if (!this.q.q(true, false)) {
         return null;
      } else if (!(this.q.oW() instanceof Ux var3)) {
         return null;
      } else if (!this.RF.RF()) {
         return null;
      } else {
         ArrayList var4 = new ArrayList();
         if (var1 == null || var1.contains(-1)) {
            try {
               ITypedValue var5 = var3.HF().q(this.q, this.RF.getId(), this.xK);
               if (var5 != null) {
                  var4.add(new LR("this", var5, 0, this.q, null));
               }
            } catch (zy | IOException var24) {
               lm.catching(var24);
            }
         }

         try {
            com.pnfsoftware.jeb.corei.parsers.dex.bK var27 = this.q.xK(this.Dw);
            IDecompilerUnit var6 = var27 == null ? null : DecompilerHelper.getDecompiler(var27);
            String var7 = null;
            int var8 = -1;
            int var9 = this.Dw.lastIndexOf(43);
            if (var9 >= 0) {
               var7 = this.Dw.substring(0, var9);
               var8 = Conversion.stringToInt(this.Dw.substring(var9 + 1), -1);
            }

            ArrayList var10 = new ArrayList();
            int var11 = 0;

            while (var11 < this.Uv) {
               if (var1 != null && !var1.contains(var11)) {
                  var11++;
               } else {
                  String var12 = null;
                  if (var7 != null && var8 >= 0) {
                     List var13 = null;
                     if (this.zz && var6 != null) {
                        var13 = Collections.emptyList();
                     }

                     Object[] var10000 = new Object[]{var11, var13};
                     if (var13 != null) {
                        HashSet var14 = new HashSet(var13);
                        if (var14.size() == 1) {
                           var12 = (String)var13.get(0);
                        }
                     }
                  }

                  byte var31 = this.q(var12);
                  if (var31 == 74 || var31 == 68) {
                     var11++;
                  }

                  if (var31 != 0) {
                     var10.add(new hf(this.RF(var11), var31));
                  }

                  var11++;
               }
            }

            for (hf var32 : var10) {
               if (this.gO.containsKey(var32.q)) {
                  var32.RF = (Byte)this.gO.get(var32.q);
               }
            }

            ArrayList var30 = new ArrayList();
            int var33 = 0;
            boolean var34 = false;

            while (var33 < var10.size()) {
               hf var15 = (hf)var10.get(var33);
               ch var16 = null;
               byte var17 = var15.RF;

               try {
                  var16 = var3.zz().q(this.RF.getId(), this.xK, var15);
                  Object[] var39 = new Object[]{var15, var16};
               } catch (Fx var25) {
                  Object[] var38 = new Object[]{var15, var25};
                  if (var25.RF() != FZ.Me.q()) {
                     if (var25.RF() != FZ.Hk.q()) {
                        throw var25;
                     }

                     if (!var34 && Pi.q(var17)) {
                        var15.RF = 76;
                        var34 = true;
                        continue;
                     }
                  }
               }

               var30.add(var16);
               var34 = false;
               var33++;
            }

            var11 = 0;

            for (ch var36 : var30) {
               int var37 = ((hf)var10.get(var11)).q;
               int var18 = this.xK(var37);
               int var19 = this.Uv(var37);
               String var20 = "v" + var18;
               String var21 = null;
               if (var19 < var18) {
                  var21 = "p" + var19;
               }

               Object var22;
               if (var36 != null) {
                  var22 = this.q.q(var36);
               } else {
                  var22 = new ValueVoid();
               }

               if (var22 != null) {
                  LR var23 = new LR(var20, (ITypedValue)var22, 0, this);
                  var23.q(var21);
                  var4.add(var23);
               }

               var11++;
            }

            return var4;
         } catch (Fx | zy | IOException var26) {
            lm.catching(var26);
            return var4;
         }
      }
   }

   public boolean q(String var1, ITypedValue var2) {
      int var3 = this.RF(var1);
      if (var3 < 0) {
         return false;
      } else {
         LR var4 = new LR(var1, var2, 0, this);
         return this.setVariable(var4);
      }
   }

   @Override
   public boolean setVariable(IDebuggerVariable var1) {
      if (!this.q.q(true, false)) {
         return false;
      } else if (!(this.q.oW() instanceof Ux var3)) {
         return false;
      } else if (!this.RF.RF()) {
         return false;
      } else {
         try {
            ch var4 = this.q.q(var1.getTypedValue());
            boolean var5 = false;
            if (var1 instanceof LR var6) {
               if (var6.RF()) {
                  var5 = var3.zz().q(this.RF.getId(), this.xK, this.q(var1), var4);
               } else if (var6.xK() != null) {
                  LR.eo var7 = var6.xK();
                  if (var7.RF()) {
                     var5 = var3.zz().q(var7.q, var7.xK, var4);
                  } else if ((var1.getFlags() & 8) != 0) {
                     var5 = var3.zz().q(var7.q, Long.valueOf(var7.RF), var4);
                  } else {
                     var5 = var3.zz().RF(var7.q, var7.RF, var4);
                  }
               }
            }

            if (var5) {
               this.q.notifyListeners(new JebEvent(J.UnitChange, null));
            }

            return var5;
         } catch (Fx | IOException var8) {
            lm.catching(var8);
         } catch (zy var9) {
         }

         return false;
      }
   }

   private boolean xK() {
      IDalvikDebuggerUnit.ThreadFrameSlotIndexMode var1 = this.q.gP();
      return var1 == IDalvikDebuggerUnit.ThreadFrameSlotIndexMode.VAR;
   }

   private int RF(int var1) {
      return this.xK() ? var1 : this.oW(var1);
   }

   private int xK(int var1) {
      return this.xK() ? var1 : this.gO(var1);
   }

   private int Dw(int var1) {
      return this.xK() ? this.gO(var1) : var1;
   }

   private int Uv(int var1) {
      return this.xK() ? this.oW(var1) : var1;
   }

   private int oW(int var1) {
      return this.q(var1, this.Uv, this.oW);
   }

   private int q(int var1, int var2, int var3) {
      int var4 = var1 - (var2 - var3);
      if (var4 < 0) {
         var4 += var2;
      }

      return var4;
   }

   private int gO(int var1) {
      return this.RF(var1, this.Uv, this.oW);
   }

   private int RF(int var1, int var2, int var3) {
      int var4 = var1 + (var2 - var3);
      if (var4 >= var2) {
         var4 -= var2;
      }

      return var4;
   }

   private hf q(IDebuggerVariable var1) {
      int var2 = this.RF(Integer.parseInt(var1.getName().substring(1)));
      byte var3 = 73;
      if (this.gO.containsKey(var2)) {
         var3 = (Byte)this.gO.get(var2);
      }

      return new hf(var2, var3);
   }

   private byte q(String var1) {
      if (var1 == null) {
         return 73;
      } else {
         byte var2 = 0;
         if (var1.equals("Ljava/lang/String;")) {
            var2 = 115;
         } else if (var1.startsWith("L")) {
            var2 = 76;
         } else if (var1.startsWith("[")) {
            var2 = 91;
         } else {
            switch (var1) {
               case "Z":
                  var2 = 90;
                  break;
               case "B":
                  var2 = 66;
                  break;
               case "C":
                  var2 = 67;
                  break;
               case "S":
                  var2 = 83;
                  break;
               case "I":
                  var2 = 73;
                  break;
               case "J":
                  var2 = 74;
                  break;
               case "F":
                  var2 = 70;
                  break;
               case "D":
                  var2 = 68;
            }
         }

         if (var2 != 0) {
            return var2;
         } else {
            String var3 = var1.toLowerCase();
            if (Strings.isContainedIn(var3, "str", "string")) {
               var2 = 115;
            } else if (Strings.isContainedIn(var3, "o", "obj", "object")) {
               var2 = 76;
            } else if (Strings.isContainedIn(var3, "a", "arr", "array")) {
               var2 = 91;
            } else if (Strings.isContainedIn(var3, "z", "bool", "boolean")) {
               var2 = 90;
            } else if (Strings.isContainedIn(var3, "b", "byte")) {
               var2 = 66;
            } else if (Strings.isContainedIn(var3, "c", "char", "character")) {
               var2 = 67;
            } else if (Strings.isContainedIn(var3, "s", "short")) {
               var2 = 83;
            } else if (Strings.isContainedIn(var3, "i", "int", "integer")) {
               var2 = 73;
            } else if (Strings.isContainedIn(var3, "j", "long")) {
               var2 = 74;
            } else if (Strings.isContainedIn(var3, "f", "float")) {
               var2 = 70;
            } else if (Strings.isContainedIn(var3, "d", "double")) {
               var2 = 68;
            }

            return var2 != 0 ? var2 : 73;
         }
      }
   }

   private int RF(String var1) {
      if (var1.startsWith("v")) {
         int var3 = Conversion.stringToInt(var1.substring(1), -1);
         return var3 < 0 ? -1 : this.RF(var3);
      } else if (var1.startsWith("p")) {
         int var2 = Conversion.stringToInt(var1.substring(1), -1);
         return var2 < 0 ? -1 : this.Dw(var2);
      } else {
         return -1;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("id:%d,address:%s", this.getId(), this.getAddress());
   }
}
