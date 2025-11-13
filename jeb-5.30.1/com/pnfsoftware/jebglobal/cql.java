package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.codeobject.ELFRelocationContext;
import com.pnfsoftware.jeb.core.units.codeobject.ELFStandardRelocator;
import com.pnfsoftware.jeb.core.units.codeobject.IELFArchRelocatorFacility;
import java.util.Arrays;
import java.util.List;

public class cql implements IELFArchRelocatorFacility {
   public static ELFStandardRelocator q = new cqm(0);
   public static ELFStandardRelocator RF = new cqx(1);
   public static ELFStandardRelocator xK = new crb(2);
   public static ELFStandardRelocator Dw = new crc(3);
   public static ELFStandardRelocator Uv = new crd(4);
   public static ELFStandardRelocator oW = new cre(5);
   public static ELFStandardRelocator gO = new crf(6);
   public static ELFStandardRelocator nf = new crg(7);
   public static ELFStandardRelocator gP = new crh(8);
   public static ELFStandardRelocator za = new cqn(9);
   public static ELFStandardRelocator lm = new cqo(10);
   public static ELFStandardRelocator zz = new cqp(11);
   public static ELFStandardRelocator JY = new cqq(12);
   public static ELFStandardRelocator HF = new cqr(13);
   public static ELFStandardRelocator LK = new cqs(14);
   public static ELFStandardRelocator io = new cqt(15);
   public static ELFStandardRelocator qa = new cqu(24);
   public static ELFStandardRelocator Hk = new cqv(37);
   public static ELFRelocationContext Me = new cqw(q, gP, LK, JY, lm, zz, RF, xK, Dw, Uv, za, HF, io, qa);
   public static ELFRelocationContext PV = new cqy(gP, RF, lm, zz, xK, gO, oW, JY, HF, LK, io, qa);
   public static ELFRelocationContext oQ = new cqz(q, Hk, gO, nf);
   public static ELFRelocationContext xW = new cra(RF);

   @Override
   public List getRelocationContexts() {
      return Arrays.asList(Me, PV, oQ, xW);
   }
}
