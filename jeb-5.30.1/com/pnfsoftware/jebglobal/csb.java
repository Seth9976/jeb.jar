package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.codeobject.ELFRelocationContext;
import com.pnfsoftware.jeb.core.units.codeobject.ELFStandardRelocator;
import com.pnfsoftware.jeb.core.units.codeobject.IELFArchRelocatorFacility;
import java.util.Arrays;
import java.util.List;

public class csb implements IELFArchRelocatorFacility {
   public static ELFStandardRelocator q = new csc(0);
   public static ELFStandardRelocator RF = new csn(1);
   public static ELFStandardRelocator xK = new csq(2);
   public static ELFStandardRelocator Dw = new csr(3);
   public static ELFStandardRelocator Uv = new css(4);
   public static ELFStandardRelocator oW = new cst(5);
   public static ELFStandardRelocator gO = new csu(6);
   public static ELFStandardRelocator nf = new csv(7);
   public static ELFStandardRelocator gP = new csw(8);
   public static ELFStandardRelocator za = new csd(9);
   public static ELFStandardRelocator lm = new cse(10);
   public static ELFStandardRelocator zz = new csf(20);
   public static ELFStandardRelocator JY = new csg(21);
   public static ELFStandardRelocator HF = new csh(22);
   public static ELFStandardRelocator LK = new csi(23);
   public static ELFStandardRelocator io = new csj(42);
   public static ELFRelocationContext qa = new csk(q, RF, xK, Dw, za, Uv, lm, oW, zz, JY, HF, LK);
   public static ELFRelocationContext Hk = new csl(q, io, gO, nf);
   public static ELFRelocationContext Me = new csm(RF);
   public static ELFRelocationContext PV = new cso(gP, RF, xK, oW, zz, JY, HF, LK);
   public static ELFRelocationContext oQ = new csp(gP, RF, xK, oW, gO, zz, JY, HF, LK);

   @Override
   public List getRelocationContexts() {
      return Arrays.asList(qa, Hk, Me, PV, oQ);
   }
}
