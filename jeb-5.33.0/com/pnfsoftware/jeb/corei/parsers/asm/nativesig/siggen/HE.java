package com.pnfsoftware.jeb.corei.parsers.asm.nativesig.siggen;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICompiler;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.LibraryID;
import com.pnfsoftware.jeb.core.units.code.asm.sig.ISigningStrategy;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Ser
public class HE {
   private static final ILogger FE = GlobalLog.getLogger(HE.class);
   @SerId(1)
   ProcessorType pC;
   @SerId(2)
   String A;
   @SerId(3)
   List kS = new ArrayList();
   @SerId(4)
   String wS;
   @SerId(5)
   String UT;
   @SerId(6)
   int E;
   @SerId(7)
   String sY;
   @SerId(8)
   String ys;
   @SerId(9)
   String ld;
   @SerId(10)
   int gp;
   @SerId(11)
   Map oT = new HashMap();
   @SerId(12)
   List fI = new ArrayList();
   @SerId(13)
   boolean WR = false;
   @SerId(14)
   boolean NS = true;
   @SerId(15)
   boolean vP = true;
   @SerId(16)
   List xC = new ArrayList();
   @SerId(17)
   ICompiler ED;
   @SerId(18)
   Set Sc = new HashSet();
   @SerId(19)
   boolean ah;
   @SerId(20)
   LibraryID eP;
   @SerId(21)
   int UO = 0;
   @SerId(22)
   String Ab;
   @SerId(23)
   ISigningStrategy rl = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.Av();
   @SerId(24)
   List z = new ArrayList();
   @SerId(25)
   List Ek = new ArrayList();
   @SerId(26)
   String hK;
   @SerId(27)
   List Er = new ArrayList();

   public void pC(String var1, String var2) {
      Object var3 = (Set)this.oT.get(var1);
      if (var3 == null) {
         var3 = new HashSet();
         this.oT.put(var1, var3);
      }

      var3.add(var2);
   }

   public boolean A(String var1, String var2) {
      Set var3 = (Set)this.oT.get(var1);
      return var3 != null && var3.contains(var2);
   }

   public List pC() {
      return this.xC;
   }

   public void pC(String var1, int var2) {
      if (this.Sc == null) {
         this.Sc = new HashSet();
      }

      Couple var3 = new Couple(var1, var2);
      this.Sc.add(var3);
   }

   public Set A() {
      return this.Sc;
   }

   public void pC(List var1) {
      this.Er = var1;
   }

   public List kS() {
      return this.Er;
   }

   public ProcessorType wS() {
      return this.pC;
   }

   public void pC(ProcessorType var1) {
      this.pC = var1;
   }

   public String UT() {
      return this.A;
   }

   public void pC(String var1) {
      this.A = var1;
   }

   public List E() {
      return this.kS;
   }

   public void A(List var1) {
      this.kS = var1;
   }

   public String sY() {
      return this.wS;
   }

   public void A(String var1) {
      this.wS = var1;
   }

   public String ys() {
      return this.UT;
   }

   public void kS(String var1) {
      this.UT = var1;
   }

   public int ld() {
      return this.E;
   }

   public void pC(int var1) {
      this.E = var1;
   }

   public String gp() {
      return this.sY;
   }

   public void wS(String var1) {
      this.sY = var1;
   }

   public String oT() {
      return this.ys;
   }

   public void UT(String var1) {
      this.ys = var1;
   }

   public String fI() {
      return this.ld;
   }

   public void E(String var1) {
      this.ld = var1;
   }

   public int WR() {
      return this.gp;
   }

   public void A(int var1) {
      this.gp = var1;
   }

   public List NS() {
      return this.fI;
   }

   public void kS(List var1) {
      this.fI = var1;
   }

   public boolean vP() {
      return this.WR;
   }

   public void pC(boolean var1) {
      this.WR = var1;
   }

   public boolean xC() {
      return this.NS;
   }

   public void A(boolean var1) {
      this.NS = var1;
   }

   public boolean ED() {
      return this.vP;
   }

   public void kS(boolean var1) {
      this.vP = var1;
   }

   public ICompiler Sc() {
      return this.ED;
   }

   public void pC(ICompiler var1) {
      this.ED = var1;
   }

   public boolean ah() {
      return this.ah;
   }

   public void wS(boolean var1) {
      this.ah = var1;
   }

   public LibraryID eP() {
      return this.eP;
   }

   public void pC(LibraryID var1) {
      this.eP = var1;
   }

   public int UO() {
      return this.UO;
   }

   public void kS(int var1) {
      this.UO = var1;
   }

   public Map Ab() {
      return this.oT;
   }

   public void wS(List var1) {
      this.xC = var1;
   }

   public String rl() {
      return this.Ab;
   }

   public void sY(String var1) {
      this.Ab = var1;
   }

   public ISigningStrategy z() {
      return this.rl;
   }

   public List Ek() {
      return this.z;
   }

   public List hK() {
      return this.Ek;
   }

   public void pC(ISigningStrategy var1) {
      this.rl = var1;
   }

   public void UT(List var1) {
      this.z = var1;
   }

   public void E(List var1) {
      this.Ek = var1;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.NS ? 1231 : 1237);
      var1 = 31 * var1 + (this.Ek == null ? 0 : this.Ek.hashCode());
      var1 = 31 * var1 + (this.xC == null ? 0 : this.xC.hashCode());
      var1 = 31 * var1 + (this.fI == null ? 0 : this.fI.hashCode());
      var1 = 31 * var1 + (this.ld == null ? 0 : this.ld.hashCode());
      var1 = 31 * var1 + (this.vP ? 1231 : 1237);
      var1 = 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
      var1 = 31 * var1 + (this.z == null ? 0 : this.z.hashCode());
      var1 = 31 * var1 + (this.Sc == null ? 0 : this.Sc.hashCode());
      var1 = 31 * var1 + (this.Er == null ? 0 : this.Er.hashCode());
      var1 = 31 * var1 + (this.Ab == null ? 0 : this.Ab.hashCode());
      var1 = 31 * var1 + (this.ah ? 1231 : 1237);
      var1 = 31 * var1 + (this.eP == null ? 0 : this.eP.hashCode());
      var1 = 31 * var1 + (this.wS == null ? 0 : this.wS.hashCode());
      var1 = 31 * var1 + (this.ys == null ? 0 : this.ys.hashCode());
      var1 = 31 * var1 + (this.sY == null ? 0 : this.sY.hashCode());
      var1 = 31 * var1 + (this.UT == null ? 0 : this.UT.hashCode());
      var1 = 31 * var1 + this.E;
      var1 = 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
      var1 = 31 * var1 + (this.oT == null ? 0 : this.oT.hashCode());
      var1 = 31 * var1 + (this.rl == null ? 0 : this.rl.hashCode());
      var1 = 31 * var1 + this.gp;
      var1 = 31 * var1 + (this.ED == null ? 0 : this.ED.hashCode());
      var1 = 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
      var1 = 31 * var1 + (this.hK == null ? 0 : this.hK.hashCode());
      var1 = 31 * var1 + (this.WR ? 1231 : 1237);
      return 31 * var1 + this.UO;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         HE var2 = (HE)var1;
         if (this.NS != var2.NS) {
            return false;
         } else {
            if (this.Ek == null) {
               if (var2.Ek != null) {
                  return false;
               }
            } else if (!this.Ek.equals(var2.Ek)) {
               return false;
            }

            if (this.xC == null) {
               if (var2.xC != null) {
                  return false;
               }
            } else if (!this.xC.equals(var2.xC)) {
               return false;
            }

            if (this.fI == null) {
               if (var2.fI != null) {
                  return false;
               }
            } else if (!this.fI.equals(var2.fI)) {
               return false;
            }

            if (this.ld == null) {
               if (var2.ld != null) {
                  return false;
               }
            } else if (!this.ld.equals(var2.ld)) {
               return false;
            }

            if (this.vP != var2.vP) {
               return false;
            } else {
               if (this.kS == null) {
                  if (var2.kS != null) {
                     return false;
                  }
               } else if (!this.kS.equals(var2.kS)) {
                  return false;
               }

               if (this.z == null) {
                  if (var2.z != null) {
                     return false;
                  }
               } else if (!this.z.equals(var2.z)) {
                  return false;
               }

               if (this.Sc == null) {
                  if (var2.Sc != null) {
                     return false;
                  }
               } else if (!this.Sc.equals(var2.Sc)) {
                  return false;
               }

               if (this.Er == null) {
                  if (var2.Er != null) {
                     return false;
                  }
               } else if (!this.Er.equals(var2.Er)) {
                  return false;
               }

               if (this.Ab == null) {
                  if (var2.Ab != null) {
                     return false;
                  }
               } else if (!this.Ab.equals(var2.Ab)) {
                  return false;
               }

               if (this.ah != var2.ah) {
                  return false;
               } else if (this.eP != var2.eP) {
                  return false;
               } else {
                  if (this.wS == null) {
                     if (var2.wS != null) {
                        return false;
                     }
                  } else if (!this.wS.equals(var2.wS)) {
                     return false;
                  }

                  if (this.ys == null) {
                     if (var2.ys != null) {
                        return false;
                     }
                  } else if (!this.ys.equals(var2.ys)) {
                     return false;
                  }

                  if (this.sY == null) {
                     if (var2.sY != null) {
                        return false;
                     }
                  } else if (!this.sY.equals(var2.sY)) {
                     return false;
                  }

                  if (this.UT == null) {
                     if (var2.UT != null) {
                        return false;
                     }
                  } else if (!this.UT.equals(var2.UT)) {
                     return false;
                  }

                  if (this.E != var2.E) {
                     return false;
                  } else if (this.pC != var2.pC) {
                     return false;
                  } else {
                     if (this.oT == null) {
                        if (var2.oT != null) {
                           return false;
                        }
                     } else if (!this.oT.equals(var2.oT)) {
                        return false;
                     }

                     if (this.rl == null) {
                        if (var2.rl != null) {
                           return false;
                        }
                     } else if (!this.rl.getClass().equals(var2.rl.getClass())) {
                        return false;
                     }

                     if (this.gp != var2.gp) {
                        return false;
                     } else {
                        if (this.ED == null) {
                           if (var2.ED != null) {
                              return false;
                           }
                        } else if (!this.ED.getClass().equals(var2.ED.getClass())) {
                           return false;
                        }

                        if (this.A == null) {
                           if (var2.A != null) {
                              return false;
                           }
                        } else if (!this.A.equals(var2.A)) {
                           return false;
                        }

                        if (this.hK == null) {
                           if (var2.hK != null) {
                              return false;
                           }
                        } else if (!this.hK.equals(var2.hK)) {
                           return false;
                        }

                        return this.WR != var2.WR ? false : this.UO == var2.UO;
                     }
                  }
               }
            }
         }
      }
   }

   public String Er() {
      return this.hK;
   }

   public void ys(String var1) {
      this.hK = var1;
   }
}
