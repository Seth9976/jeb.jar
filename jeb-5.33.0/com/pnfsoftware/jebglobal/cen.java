package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Random;

public class cen {
   static final String pC = ckx.pC(
         "Name:\t%s\nUmask:\t0077\nState:\tR (running)\nTgid:\t%d\nNgid:\t0\nPid:\t%d\nPPid:\t%d\nTracerPid:\t0\nUid:\t10284\t10284\t10284\t10284\nGid:\t10284\t10284\t10284\t10284\nFDSize:\t128\nGroups:\t1077 9997 20284 50284\nVmPeak:\t15162872 kB\nVmSize:\t15162872 kB\nVmLck:\t0 kB\nVmPin:\t0 kB\nVmHWM:\t97864 kB\nVmRSS:\t97864 kB\nRssAnon:\t46236 kB\nRssFile:\t49724 kB\nRssShmem:\t1904 kB\nVmData:\t1755804 kB\nVmStk:\t8192 kB\nVmExe:\t12 kB\nVmLib:\t151744 kB\nVmPTE:\t1056 kB\nVmPMD:\t64 kB\nVmSwap:\t8072 kB\nThreads:\t%d\nSigQ:\t0/21242\nSigPnd:\t0000000000000000\nShdPnd:\t0000000000000000\nSigBlk:\t0000000080001204\nSigIgn:\t0000000000000001\nSigCgt:\t0000006e400084f8\nCapInh:\t0000000000000000\nCapPrm:\t0000000000000000\nCapEff:\t0000000000000000\nCapBnd:\t0000000000000000\nCapAmb:\t0000000000000000\nNoNewPrivs:\t0\nSeccomp:\t2\nSpeculation_Store_Bypass:\tunknown\nCpus_allowed:\tff\nCpus_allowed_list:\t0-7\nMems_allowed:\t1\nMems_allowed_list:\t0\nvoluntary_ctxt_switches:\t103\nnonvoluntary_ctxt_switches:\t19\n"
      )
      .replace("\r\n", "\n");
   String A;
   int kS;
   int wS;
   int UT;

   public cen(String var1, Integer var2, Integer var3) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.A = var1;
         Random var4 = new Random(var1.hashCode());
         if (var2 == null) {
            var2 = 1000 + var4.nextInt(19000);
         }

         this.kS = var2;
         if (var3 == null) {
            var3 = 1000 + var4.nextInt(19000);
         }

         this.wS = var3;
         this.UT = var2;
      }
   }

   public String pC(Integer var1) {
      int var2;
      if (var1 != null) {
         var2 = var1;
      } else {
         var2 = 12;
      }

      String var3 = this.A;
      if (this.A.length() > 15) {
         var3 = this.A.substring(this.A.length() - 15, this.A.length());
      }

      return Strings.ff(pC, var3, this.UT, this.kS, this.wS, var2);
   }
}
