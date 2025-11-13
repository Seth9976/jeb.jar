package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Random;

public class cjy {
   private static final String Uv = "Name:   %s\nUmask:  0077\nState:  R (running)\nTgid:   %d\nNgid:   0\nPid:    %d\nPPid:   %d\nTracerPid:  0\nUid:    10002   10002   10002   10002\nGid:    10002   10002   10002   10002\nFDSize: 128\nGroups: 1077 9997 20002 50002 \nVmPeak: 15182764 kB\nVmSize: 14693152 kB\nVmLck:         0 kB\nVmPin:         0 kB\nVmHWM:     80576 kB\nVmRSS:     80576 kB\nRssAnon:       30444 kB\nRssFile:       49312 kB\nRssShmem:        820 kB\nVmData:  1226580 kB\nVmStk:      8192 kB\nVmExe:        12 kB\nVmLib:    147820 kB\nVmPTE:       884 kB\nVmPMD:        72 kB\nVmSwap:    18972 kB\nThreads:    %d\nSigQ:   0/21194\nSigPnd: 0000000000000000\nShdPnd: 0000000000000000\nSigBlk: 0000000080001204\nSigIgn: 0000000000000001\nSigCgt: 0000006e400084f8\nCapInh: 0000000000000000\nCapPrm: 0000000000000000\nCapEff: 0000000000000000\nCapBnd: 0000000000000000\nCapAmb: 0000000000000000\nNoNewPrivs: 0\nSeccomp:    2\nSpeculation_Store_Bypass:   unknown\nCpus_allowed:   ff\nCpus_allowed_list:  0-7\nMems_allowed:   1\nMems_allowed_list:  0\nvoluntary_ctxt_switches:    95\nnonvoluntary_ctxt_switches: 71\n";
   String q;
   int RF;
   int xK;
   int Dw;

   public cjy(String var1, Integer var2, Integer var3) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.q = var1;
         Random var4 = new Random(var1.hashCode());
         if (var2 == null) {
            var2 = 1000 + var4.nextInt(19000);
         }

         this.RF = var2;
         if (var3 == null) {
            var3 = 1000 + var4.nextInt(19000);
         }

         this.xK = var3;
         this.Dw = var2;
      }
   }

   public String q(Integer var1) {
      int var2;
      if (var1 != null) {
         var2 = var1;
      } else {
         var2 = 15;
      }

      return Strings.ff(
         "Name:   %s\nUmask:  0077\nState:  R (running)\nTgid:   %d\nNgid:   0\nPid:    %d\nPPid:   %d\nTracerPid:  0\nUid:    10002   10002   10002   10002\nGid:    10002   10002   10002   10002\nFDSize: 128\nGroups: 1077 9997 20002 50002 \nVmPeak: 15182764 kB\nVmSize: 14693152 kB\nVmLck:         0 kB\nVmPin:         0 kB\nVmHWM:     80576 kB\nVmRSS:     80576 kB\nRssAnon:       30444 kB\nRssFile:       49312 kB\nRssShmem:        820 kB\nVmData:  1226580 kB\nVmStk:      8192 kB\nVmExe:        12 kB\nVmLib:    147820 kB\nVmPTE:       884 kB\nVmPMD:        72 kB\nVmSwap:    18972 kB\nThreads:    %d\nSigQ:   0/21194\nSigPnd: 0000000000000000\nShdPnd: 0000000000000000\nSigBlk: 0000000080001204\nSigIgn: 0000000000000001\nSigCgt: 0000006e400084f8\nCapInh: 0000000000000000\nCapPrm: 0000000000000000\nCapEff: 0000000000000000\nCapBnd: 0000000000000000\nCapAmb: 0000000000000000\nNoNewPrivs: 0\nSeccomp:    2\nSpeculation_Store_Bypass:   unknown\nCpus_allowed:   ff\nCpus_allowed_list:  0-7\nMems_allowed:   1\nMems_allowed_list:  0\nvoluntary_ctxt_switches:    95\nnonvoluntary_ctxt_switches: 71\n",
         this.q,
         this.Dw,
         this.RF,
         this.xK,
         var2
      );
   }
}
