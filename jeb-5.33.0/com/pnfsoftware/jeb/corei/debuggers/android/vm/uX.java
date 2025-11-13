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
import com.pnfsoftware.jebglobal.SL;
import com.pnfsoftware.jebglobal.bA;
import com.pnfsoftware.jebglobal.bev;
import com.pnfsoftware.jebglobal.bfu;
import com.pnfsoftware.jebglobal.eL;
import com.pnfsoftware.jebglobal.jZ;
import com.pnfsoftware.jebglobal.lM;
import com.pnfsoftware.jebglobal.oY;
import com.pnfsoftware.jebglobal.rG;
import com.pnfsoftware.jebglobal.un;
import com.pnfsoftware.jebglobal.wX;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class uX implements IDebuggerThreadStackFrame {
   private static final ILogger oT = GlobalLog.getLogger(uX.class);
   private boolean fI = false;
   Tb pC;
   Mh A;
   long kS;
   String wS;
   int UT;
   int E;
   Map sY = new HashMap();
   int ys;
   int ld;
   Map gp;

   public uX(Mh var1, long var2, String var4) {
      this.pC = var1.pC();
      this.A = var1;
      this.kS = var2;
      this.wS = var4;
      this.A();
   }

   private void A() {
      try {
         UnitAddress var1 = this.pC.kS(this.wS);
         if (var1 != null && var1.getUnit() != null) {
            ICodeCoordinates var2 = ((com.pnfsoftware.jeb.corei.parsers.dex.vi)var1.getUnit()).A().pC(this.wS);
            if (var2 instanceof InstructionCoordinates) {
               this.ys = ((InstructionCoordinates)var2).getMethodId();
               this.ld = ((InstructionCoordinates)var2).getOffset();
               bfu var3 = ((com.pnfsoftware.jeb.corei.parsers.dex.vi)var1.getUnit()).sY(this.ys);
               if (var3 != null && var3.A() != null) {
                  bev var4 = var3.A().pC();
                  this.gp = var3.getDebugVariablesMap();
                  this.UT = var4.getRegisterCount();
                  this.E = DexUtil.getMethodSlotCount(var3.UT().getShorty(), var3.A().isStatic());
               }
            }
         }
      } catch (Exception var5) {
         oT.error("Cannot retrieve frame size: %s", var5);
      }
   }

   public Mh pC() {
      return this.A;
   }

   @Override
   public long getId() {
      return this.kS;
   }

   @Override
   public String getAddress() {
      if (!this.pC.pC(true, false)) {
         return this.wS;
      } else if (!(this.pC.UT() instanceof bA var2)) {
         return null;
      } else {
         try {
            jZ var3 = var2.gp().pC(this.A.id, 0, 1);
            if (var3.pC.length <= 0) {
               return null;
            } else {
               SL var4 = var3.pC[0];
               if (var4.pC == this.kS) {
                  this.wS = this.pC.pC(var4.A);
               }

               return this.wS;
            }
         } catch (oY | IOException var5) {
            oT.catching(var5);
            return null;
         }
      }
   }

   public Kr pC(int var1, String var2) {
      if (!this.pC.pC(true, false)) {
         return null;
      } else if (!(this.pC.UT() instanceof bA var4)) {
         return null;
      } else if (!this.A.A()) {
         return null;
      } else {
         try {
            eL var5 = new eL(var1, this.pC(var2));
            rG var6 = var4.gp().pC(this.A.getId(), this.kS, var5);
            if (var6 == null) {
               return null;
            } else {
               ITypedValue var7 = this.pC.pC(var6);
               if (var7 == null) {
                  return null;
               } else {
                  String var8 = "v" + this.kS(var1);
                  return new Kr(var8, var7, 0, this);
               }
            }
         } catch (oY | wX | IOException var9) {
            oT.catching(var9);
            return null;
         }
      }
   }

   public boolean pC(String var1, String var2) {
      int var3 = this.A(var1);
      if (var3 < 0) {
         return false;
      } else {
         byte var4 = this.pC(var2);
         this.sY.put(var3, var4);
         return true;
      }
   }

   public Kr A(String var1, String var2) {
      int var3 = this.A(var1);
      if (var3 < 0) {
         return null;
      } else {
         return !Strings.isBlank(var2) ? this.pC(var3, var2) : this.pC(this.kS(var3));
      }
   }

   @Override
   public List getVariables() {
      return this.getVariables(false);
   }

   @Override
   public List getVariables(boolean var1) {
      if (var1 && this.gp != null) {
         ArrayList var2 = new ArrayList();
         var2.add(-1);

         for (IDexDebugVariable var5 : (List)this.gp.getOrDefault(this.ld, Collections.emptyList())) {
            var2.add(var5.getRegister());
         }

         return this.pC(var2);
      } else {
         return this.pC(null);
      }
   }

   public Kr pC(int var1) {
      ArrayList var2 = new ArrayList();
      var2.add(var1);
      List var3 = this.pC(var2);
      return (Kr)Lists.getFirst(var3);
   }

   public List pC(List var1) {
      if (!this.pC.pC(true, false)) {
         return null;
      } else if (!(this.pC.UT() instanceof bA var3)) {
         return null;
      } else if (!this.A.A()) {
         return null;
      } else {
         ArrayList var4 = new ArrayList();
         if (var1 == null || var1.contains(-1)) {
            try {
               ITypedValue var5 = var3.fI().pC(this.pC, this.A.getId(), this.kS);
               if (var5 != null) {
                  var4.add(new Kr("this", var5, 0, this.pC, null));
               }
            } catch (wX | IOException var24) {
               oT.catching(var24);
            }
         }

         try {
            com.pnfsoftware.jeb.corei.parsers.dex.vi var27 = this.pC.A(this.wS);
            IDecompilerUnit var6 = var27 == null ? null : DecompilerHelper.getDecompiler(var27);
            String var7 = null;
            int var8 = -1;
            int var9 = this.wS.lastIndexOf(43);
            if (var9 >= 0) {
               var7 = this.wS.substring(0, var9);
               var8 = Conversion.stringToInt(this.wS.substring(var9 + 1), -1);
            }

            ArrayList var10 = new ArrayList();
            int var11 = 0;

            while (var11 < this.UT) {
               if (var1 != null && !var1.contains(var11)) {
                  var11++;
               } else {
                  String var12 = null;
                  if (var7 != null && var8 >= 0) {
                     List var13 = null;
                     if (this.fI && var6 != null) {
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

                  byte var31 = this.pC(var12);
                  if (var31 == 74 || var31 == 68) {
                     var11++;
                  }

                  if (var31 != 0) {
                     var10.add(new eL(this.A(var11), var31));
                  }

                  var11++;
               }
            }

            for (eL var32 : var10) {
               if (this.sY.containsKey(var32.pC)) {
                  var32.A = (Byte)this.sY.get(var32.pC);
               }
            }

            ArrayList var30 = new ArrayList();
            int var33 = 0;
            boolean var34 = false;

            while (var33 < var10.size()) {
               eL var15 = (eL)var10.get(var33);
               rG var16 = null;
               byte var17 = var15.A;

               try {
                  var16 = var3.gp().pC(this.A.getId(), this.kS, var15);
                  Object[] var39 = new Object[]{var15, var16};
               } catch (oY var25) {
                  Object[] var38 = new Object[]{var15, var25};
                  if (var25.pC() != lM.ah.pC()) {
                     if (var25.pC() != lM.Sc.pC()) {
                        throw var25;
                     }

                     if (!var34 && un.pC(var17)) {
                        var15.A = 76;
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

            for (rG var36 : var30) {
               int var37 = ((eL)var10.get(var11)).pC;
               int var18 = this.kS(var37);
               int var19 = this.UT(var37);
               String var20 = "v" + var18;
               String var21 = null;
               if (var19 < var18) {
                  var21 = "p" + var19;
               }

               Object var22;
               if (var36 != null) {
                  var22 = this.pC.pC(var36);
               } else {
                  var22 = new ValueVoid();
               }

               if (var22 != null) {
                  Kr var23 = new Kr(var20, (ITypedValue)var22, 0, this);
                  var23.pC(var21);
                  var4.add(var23);
               }

               var11++;
            }

            return var4;
         } catch (oY | wX | IOException var26) {
            oT.catching(var26);
            return var4;
         }
      }
   }

   public boolean pC(String var1, ITypedValue var2) {
      int var3 = this.A(var1);
      if (var3 < 0) {
         return false;
      } else {
         Kr var4 = new Kr(var1, var2, 0, this);
         return this.setVariable(var4);
      }
   }

   @Override
   public boolean setVariable(IDebuggerVariable var1) {
      if (!this.pC.pC(true, false)) {
         return false;
      } else if (!(this.pC.UT() instanceof bA var3)) {
         return false;
      } else if (!this.A.A()) {
         return false;
      } else {
         try {
            rG var4 = this.pC.pC(var1.getTypedValue());
            boolean var5 = false;
            if (var1 instanceof Kr var6) {
               if (var6.A()) {
                  var5 = var3.gp().pC(this.A.getId(), this.kS, this.pC(var1), var4);
               } else if (var6.kS() != null) {
                  Kr.Av var7 = var6.kS();
                  if (var7.pC()) {
                     var5 = var3.gp().pC(var7.pC, var7.kS, var4);
                  } else if ((var1.getFlags() & 8) != 0) {
                     var5 = var3.gp().pC(var7.pC, Long.valueOf(var7.A), var4);
                  } else {
                     var5 = var3.gp().A(var7.pC, var7.A, var4);
                  }
               }
            }

            if (var5) {
               this.pC.notifyListeners(new JebEvent(J.UnitChange, null));
            }

            return var5;
         } catch (oY | IOException var8) {
            oT.catching(var8);
         } catch (wX var9) {
         }

         return false;
      }
   }

   private boolean kS() {
      IDalvikDebuggerUnit.ThreadFrameSlotIndexMode var1 = this.pC.ys();
      return var1 == IDalvikDebuggerUnit.ThreadFrameSlotIndexMode.VAR;
   }

   private int A(int var1) {
      return this.kS() ? var1 : this.E(var1);
   }

   private int kS(int var1) {
      return this.kS() ? var1 : this.sY(var1);
   }

   private int wS(int var1) {
      return this.kS() ? this.sY(var1) : var1;
   }

   private int UT(int var1) {
      return this.kS() ? this.E(var1) : var1;
   }

   private int E(int var1) {
      return this.pC(var1, this.UT, this.E);
   }

   private int pC(int var1, int var2, int var3) {
      int var4 = var1 - (var2 - var3);
      if (var4 < 0) {
         var4 += var2;
      }

      return var4;
   }

   private int sY(int var1) {
      return this.A(var1, this.UT, this.E);
   }

   private int A(int var1, int var2, int var3) {
      int var4 = var1 + (var2 - var3);
      if (var4 >= var2) {
         var4 -= var2;
      }

      return var4;
   }

   private eL pC(IDebuggerVariable var1) {
      int var2 = this.A(Integer.parseInt(var1.getName().substring(1)));
      byte var3 = 73;
      if (this.sY.containsKey(var2)) {
         var3 = (Byte)this.sY.get(var2);
      }

      return new eL(var2, var3);
   }

   private byte pC(String var1) {
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

   private int A(String var1) {
      if (var1.startsWith("v")) {
         int var3 = Conversion.stringToInt(var1.substring(1), -1);
         return var3 < 0 ? -1 : this.A(var3);
      } else if (var1.startsWith("p")) {
         int var2 = Conversion.stringToInt(var1.substring(1), -1);
         return var2 < 0 ? -1 : this.wS(var2);
      } else {
         return -1;
      }
   }

   @Override
   public String toString() {
      return Strings.ff("id:%d,address:%s", this.getId(), this.getAddress());
   }
}
