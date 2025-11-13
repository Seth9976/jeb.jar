package com.pnfsoftware.jebglobal;

class azb {
   azd q;
   int RF;
   int xK;
   azb.eo Dw;
   boolean Uv = false;
   String oW;

   azb() {
   }

   azb q() {
      return this.Dw.lm().q;
   }

   azb RF() {
      return this.Dw.lm().RF;
   }

   public void q(azb var1) {
      this.Dw.lm().q = var1;
   }

   public void RF(azb var1) {
      this.Dw.lm().RF = var1;
   }

   azb(azb var1) {
      this.q = var1.q;
      this.RF = var1.RF;
      this.Dw = var1.Dw;
   }

   static enum Bu {
      q,
      RF,
      xK,
      Dw,
      Uv;
   }

   static class CU implements azb.eo {
      azb q;
      azb RF;
   }

   static class EE implements azb.eo {
      azb q;
      boolean RF;
      boolean xK;
   }

   static class Nt extends azb.Vj {
      azb.bK q;
   }

   static class PY implements azb.eo {
      aze q;
   }

   abstract static class Vj implements azb.eo {
      azb RF;
   }

   static enum bK {
      q,
      RF,
      xK,
      Dw,
      Uv;
   }

   static class ej implements azb.eo {
      int q;
   }

   interface eo {
      default azb.qV q() {
         return (azb.qV)this;
      }

      default azb.PY RF() {
         return (azb.PY)this;
      }

      default azb.tw xK() {
         return (azb.tw)this;
      }

      default azb.oM Dw() {
         return (azb.oM)this;
      }

      default azb.Nt Uv() {
         return (azb.Nt)this;
      }

      default azb.nI oW() {
         return (azb.nI)this;
      }

      default azb.iZ gO() {
         return (azb.iZ)this;
      }

      default azb.vb nf() {
         return (azb.vb)this;
      }

      default azb.vn gP() {
         return (azb.vn)this;
      }

      default azb.ej za() {
         return (azb.ej)this;
      }

      default azb.CU lm() {
         return (azb.CU)this;
      }

      default azb.oL zz() {
         return (azb.oL)this;
      }

      default azb.EE JY() {
         return (azb.EE)this;
      }
   }

   static class iZ implements azb.eo {
      aza q;
      ayz RF;
      boolean xK;
      char Dw;
   }

   static class nI implements azb.eo {
      aza q;
   }

   static class oL implements azb.eo {
      azb q;
      int RF;
   }

   static class oM extends azb.Vj {
      azb.Bu q;
   }

   static class qV implements azb.eo {
      String q;
   }

   static class tw extends azb.Vj {
      int q;
   }

   static class vb implements azb.eo {
      String q;
   }

   static class vn implements azb.eo {
      long q;
   }
}
