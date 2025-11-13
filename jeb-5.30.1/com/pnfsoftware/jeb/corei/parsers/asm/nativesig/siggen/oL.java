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
public class oL {
   private static final ILogger CE = GlobalLog.getLogger(oL.class);
   @SerId(1)
   ProcessorType q;
   @SerId(2)
   String RF;
   @SerId(3)
   List xK = new ArrayList();
   @SerId(4)
   String Dw;
   @SerId(5)
   String Uv;
   @SerId(6)
   int oW;
   @SerId(7)
   String gO;
   @SerId(8)
   String nf;
   @SerId(9)
   String gP;
   @SerId(10)
   int za;
   @SerId(11)
   Map lm = new HashMap();
   @SerId(12)
   List zz = new ArrayList();
   @SerId(13)
   boolean JY = false;
   @SerId(14)
   boolean HF = true;
   @SerId(15)
   boolean LK = true;
   @SerId(16)
   List io = new ArrayList();
   @SerId(17)
   ICompiler qa;
   @SerId(18)
   Set Hk = new HashSet();
   @SerId(19)
   boolean Me;
   @SerId(20)
   LibraryID PV;
   @SerId(21)
   int oQ = 0;
   @SerId(22)
   String xW;
   @SerId(23)
   ISigningStrategy KT = new com.pnfsoftware.jeb.corei.parsers.asm.nativesig.eo();
   @SerId(24)
   List Gf = new ArrayList();
   @SerId(25)
   List Ef = new ArrayList();
   @SerId(26)
   String cC;
   @SerId(27)
   List sH = new ArrayList();

   public void q(String var1, String var2) {
      Object var3 = (Set)this.lm.get(var1);
      if (var3 == null) {
         var3 = new HashSet();
         this.lm.put(var1, var3);
      }

      var3.add(var2);
   }

   public boolean RF(String var1, String var2) {
      Set var3 = (Set)this.lm.get(var1);
      return var3 != null && var3.contains(var2);
   }

   public void q(String var1) {
      if (this.io == null) {
         this.io = new ArrayList();
      }

      this.io.add(var1);
   }

   public List q() {
      return this.io;
   }

   public void q(String var1, int var2) {
      if (this.Hk == null) {
         this.Hk = new HashSet();
      }

      Couple var3 = new Couple(var1, var2);
      this.Hk.add(var3);
   }

   public Set RF() {
      return this.Hk;
   }

   public void q(List var1) {
      this.sH = var1;
   }

   public List xK() {
      return this.sH;
   }

   public void Dw() {
      Object[] var10000 = new Object[]{this.q};
      if (this.qa != null) {
         var10000 = new Object[]{this.qa.getClass().getSimpleName()};
      }

      var10000 = new Object[]{this.oW};
      var10000 = new Object[]{this.oQ};
      if (this.PV != null) {
         var10000 = new Object[]{this.PV};
      }

      var10000 = new Object[]{this.RF};
      var10000 = new Object[]{this.Dw};
      var10000 = new Object[]{this.Uv};
      var10000 = new Object[]{this.gO};
      var10000 = new Object[]{this.nf};
      var10000 = new Object[]{this.gP};

      for (String var2 : this.xK) {
         var10000 = new Object[]{var2};
      }

      for (String var9 : this.lm.keySet()) {
         for (String var4 : (Set)this.lm.get(var9)) {
            var10000 = new Object[]{var9, var4};
         }
      }

      if (this.io != null) {
         for (String var10 : this.io) {
            var10000 = new Object[]{var10};
         }
      }

      for (String var11 : this.zz) {
         var10000 = new Object[]{var11};
      }

      if (this.Hk != null) {
         for (Couple var12 : this.Hk) {
            var10000 = new Object[]{var12.getFirst(), var12.getSecond()};
         }
      }

      var10000 = new Object[]{this.JY};
      var10000 = new Object[]{this.HF};
      var10000 = new Object[]{this.LK};
      var10000 = new Object[]{this.Me};
   }

   public ProcessorType Uv() {
      return this.q;
   }

   public void q(ProcessorType var1) {
      this.q = var1;
   }

   public String oW() {
      return this.RF;
   }

   public void RF(String var1) {
      this.RF = var1;
   }

   public List gO() {
      return this.xK;
   }

   public void RF(List var1) {
      this.xK = var1;
   }

   public String nf() {
      return this.Dw;
   }

   public void xK(String var1) {
      this.Dw = var1;
   }

   public String gP() {
      return this.Uv;
   }

   public void Dw(String var1) {
      this.Uv = var1;
   }

   public int za() {
      return this.oW;
   }

   public void q(int var1) {
      this.oW = var1;
   }

   public String lm() {
      return this.gO;
   }

   public void Uv(String var1) {
      this.gO = var1;
   }

   public String zz() {
      return this.nf;
   }

   public void oW(String var1) {
      this.nf = var1;
   }

   public String JY() {
      return this.gP;
   }

   public void gO(String var1) {
      this.gP = var1;
   }

   public int HF() {
      return this.za;
   }

   public void RF(int var1) {
      this.za = var1;
   }

   public List LK() {
      return this.zz;
   }

   public void xK(List var1) {
      this.zz = var1;
   }

   public boolean io() {
      return this.JY;
   }

   public void q(boolean var1) {
      this.JY = var1;
   }

   public boolean qa() {
      return this.HF;
   }

   public void RF(boolean var1) {
      this.HF = var1;
   }

   public boolean Hk() {
      return this.LK;
   }

   public void xK(boolean var1) {
      this.LK = var1;
   }

   public ICompiler Me() {
      return this.qa;
   }

   public void q(ICompiler var1) {
      this.qa = var1;
   }

   public boolean PV() {
      return this.Me;
   }

   public void Dw(boolean var1) {
      this.Me = var1;
   }

   public LibraryID oQ() {
      return this.PV;
   }

   public void q(LibraryID var1) {
      this.PV = var1;
   }

   public int xW() {
      return this.oQ;
   }

   public void xK(int var1) {
      this.oQ = var1;
   }

   public Map KT() {
      return this.lm;
   }

   public void nf(String var1) {
      this.xK.add(var1);
   }

   public void gP(String var1) {
      this.zz.add(var1);
   }

   public void Dw(List var1) {
      this.io = var1;
   }

   public String Gf() {
      return this.xW;
   }

   public void za(String var1) {
      this.xW = var1;
   }

   public ISigningStrategy Ef() {
      return this.KT;
   }

   public List cC() {
      return this.Gf;
   }

   public List sH() {
      return this.Ef;
   }

   public void q(ISigningStrategy var1) {
      this.KT = var1;
   }

   public void Uv(List var1) {
      this.Gf = var1;
   }

   public void oW(List var1) {
      this.Ef = var1;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.HF ? 1231 : 1237);
      var1 = 31 * var1 + (this.Ef == null ? 0 : this.Ef.hashCode());
      var1 = 31 * var1 + (this.io == null ? 0 : this.io.hashCode());
      var1 = 31 * var1 + (this.zz == null ? 0 : this.zz.hashCode());
      var1 = 31 * var1 + (this.gP == null ? 0 : this.gP.hashCode());
      var1 = 31 * var1 + (this.LK ? 1231 : 1237);
      var1 = 31 * var1 + (this.xK == null ? 0 : this.xK.hashCode());
      var1 = 31 * var1 + (this.Gf == null ? 0 : this.Gf.hashCode());
      var1 = 31 * var1 + (this.Hk == null ? 0 : this.Hk.hashCode());
      var1 = 31 * var1 + (this.sH == null ? 0 : this.sH.hashCode());
      var1 = 31 * var1 + (this.xW == null ? 0 : this.xW.hashCode());
      var1 = 31 * var1 + (this.Me ? 1231 : 1237);
      var1 = 31 * var1 + (this.PV == null ? 0 : this.PV.hashCode());
      var1 = 31 * var1 + (this.Dw == null ? 0 : this.Dw.hashCode());
      var1 = 31 * var1 + (this.nf == null ? 0 : this.nf.hashCode());
      var1 = 31 * var1 + (this.gO == null ? 0 : this.gO.hashCode());
      var1 = 31 * var1 + (this.Uv == null ? 0 : this.Uv.hashCode());
      var1 = 31 * var1 + this.oW;
      var1 = 31 * var1 + (this.q == null ? 0 : this.q.hashCode());
      var1 = 31 * var1 + (this.lm == null ? 0 : this.lm.hashCode());
      var1 = 31 * var1 + (this.KT == null ? 0 : this.KT.hashCode());
      var1 = 31 * var1 + this.za;
      var1 = 31 * var1 + (this.qa == null ? 0 : this.qa.hashCode());
      var1 = 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
      var1 = 31 * var1 + (this.cC == null ? 0 : this.cC.hashCode());
      var1 = 31 * var1 + (this.JY ? 1231 : 1237);
      return 31 * var1 + this.oQ;
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
         oL var2 = (oL)var1;
         if (this.HF != var2.HF) {
            return false;
         } else {
            if (this.Ef == null) {
               if (var2.Ef != null) {
                  return false;
               }
            } else if (!this.Ef.equals(var2.Ef)) {
               return false;
            }

            if (this.io == null) {
               if (var2.io != null) {
                  return false;
               }
            } else if (!this.io.equals(var2.io)) {
               return false;
            }

            if (this.zz == null) {
               if (var2.zz != null) {
                  return false;
               }
            } else if (!this.zz.equals(var2.zz)) {
               return false;
            }

            if (this.gP == null) {
               if (var2.gP != null) {
                  return false;
               }
            } else if (!this.gP.equals(var2.gP)) {
               return false;
            }

            if (this.LK != var2.LK) {
               return false;
            } else {
               if (this.xK == null) {
                  if (var2.xK != null) {
                     return false;
                  }
               } else if (!this.xK.equals(var2.xK)) {
                  return false;
               }

               if (this.Gf == null) {
                  if (var2.Gf != null) {
                     return false;
                  }
               } else if (!this.Gf.equals(var2.Gf)) {
                  return false;
               }

               if (this.Hk == null) {
                  if (var2.Hk != null) {
                     return false;
                  }
               } else if (!this.Hk.equals(var2.Hk)) {
                  return false;
               }

               if (this.sH == null) {
                  if (var2.sH != null) {
                     return false;
                  }
               } else if (!this.sH.equals(var2.sH)) {
                  return false;
               }

               if (this.xW == null) {
                  if (var2.xW != null) {
                     return false;
                  }
               } else if (!this.xW.equals(var2.xW)) {
                  return false;
               }

               if (this.Me != var2.Me) {
                  return false;
               } else if (this.PV != var2.PV) {
                  return false;
               } else {
                  if (this.Dw == null) {
                     if (var2.Dw != null) {
                        return false;
                     }
                  } else if (!this.Dw.equals(var2.Dw)) {
                     return false;
                  }

                  if (this.nf == null) {
                     if (var2.nf != null) {
                        return false;
                     }
                  } else if (!this.nf.equals(var2.nf)) {
                     return false;
                  }

                  if (this.gO == null) {
                     if (var2.gO != null) {
                        return false;
                     }
                  } else if (!this.gO.equals(var2.gO)) {
                     return false;
                  }

                  if (this.Uv == null) {
                     if (var2.Uv != null) {
                        return false;
                     }
                  } else if (!this.Uv.equals(var2.Uv)) {
                     return false;
                  }

                  if (this.oW != var2.oW) {
                     return false;
                  } else if (this.q != var2.q) {
                     return false;
                  } else {
                     if (this.lm == null) {
                        if (var2.lm != null) {
                           return false;
                        }
                     } else if (!this.lm.equals(var2.lm)) {
                        return false;
                     }

                     if (this.KT == null) {
                        if (var2.KT != null) {
                           return false;
                        }
                     } else if (!this.KT.getClass().equals(var2.KT.getClass())) {
                        return false;
                     }

                     if (this.za != var2.za) {
                        return false;
                     } else {
                        if (this.qa == null) {
                           if (var2.qa != null) {
                              return false;
                           }
                        } else if (!this.qa.getClass().equals(var2.qa.getClass())) {
                           return false;
                        }

                        if (this.RF == null) {
                           if (var2.RF != null) {
                              return false;
                           }
                        } else if (!this.RF.equals(var2.RF)) {
                           return false;
                        }

                        if (this.cC == null) {
                           if (var2.cC != null) {
                              return false;
                           }
                        } else if (!this.cC.equals(var2.cC)) {
                           return false;
                        }

                        return this.JY != var2.JY ? false : this.oQ == var2.oQ;
                     }
                  }
               }
            }
         }
      }
   }

   public String CE() {
      return this.cC;
   }

   public void lm(String var1) {
      this.cC = var1;
   }
}
