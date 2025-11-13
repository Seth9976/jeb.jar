package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.IMachineContext;
import com.pnfsoftware.jeb.core.units.code.asm.processor.ProcessorException;
import com.pnfsoftware.jeb.core.units.code.asm.simulator.IInsnEmulator;

public interface lH extends IInsnEmulator {
   int RF = 2;
   int xK = 4;
   int Dw = 3840;
   int Uv = 512;
   int oW = 768;
   int gO = 1024;
   int nf = 1280;
   int gP = 1536;
   int za = 1792;
   int lm = 2048;
   int zz = 2304;
   int JY = 2560;
   int HF = 2816;
   int LK = 61440;
   int io = 4096;
   int qa = 4096;
   int Hk = 8192;
   int Me = 12288;
   int PV = 16384;
   int oQ = 20480;
   int xW = 24576;
   int KT = 28672;
   int Gf = 32768;
   int Ef = 36864;
   int cC = 40960;
   int sH = 45056;
   int CE = 45056;
   int wF = 49152;
   int If = 53248;
   int Dz = 57344;
   int mI = 61440;
   int jq = 61440;
   int ui = 917504;
   int TX = 131072;
   int Rr = 262144;
   int EB = 524288;
   int Xo = 1048576;
   int Bu = -268435456;
   int IN = 268435456;
   int rL = 536870912;
   int eJ = 805306368;
   int YN = 1073741824;

   int q(int var1, long var2);

   boolean q();

   boolean RF();

   boolean xK();

   boolean Dw();

   OQ.eo oW();

   int getFlags();

   lH RF(CW[] var1, int var2);

   Long q(fA var1, long var2, IMachineContext var4) throws ProcessorException;

   public static enum eo {
      q,
      RF,
      xK,
      Dw,
      Uv,
      oW,
      gO;
   }
}
