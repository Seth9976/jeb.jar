package com.pnfsoftware.jebglobal;

import java.util.Arrays;
import java.util.Collection;

public class cbp extends bwj {
   static bso.Av pC = bso.Av.pC("Obfuscated integer additions")
      .pC("0 - ((0 - $0) - $1)")
      .pC("0 - (0 - ($0 + $1))")
      .pC("($0 & $1) + ($0 | $1)")
      .pC("(($0 & $1) * 2) + ($0 ^ $1)")
      .pC("(($0 | $1) * 2) - ($0 ^ $1)")
      .pC("($0 - (~ $1)) - 1")
      .A("$0 + $1")
      .A(2);
   static bso.Av A = bso.Av.pC("Obfuscated integer subtractions")
      .pC("0 - ((0 - $0) + $1)")
      .pC("0 - (0 - ($0 - $1))")
      .pC("($0 & (~ $1)) - ((~ $0) & $1)")
      .pC("($0 ^ $1) - (2 * ((~ $0) & $1))")
      .pC("(2 * ($0 & (~ $1))) - ($0 ^ $1)")
      .pC("$0 + (~ $1) + 1")
      .A("$0 - $1")
      .A(2);
   static bso.Av kS = bso.Av.pC("Obfuscated bitwise-xor [with conditions]")
      .pC("($0 + $1 + (- (($0 & $1) * 2)))")
      .pC("((2 * ($0 | $1)) + (- $0) + (- $1))")
      .pC("($0 + (- $1) + (2 * ((~ $0) & $1)))")
      .A("$0 ^ $1")
      .A(2);
   static bso.Av wS = bso.Av.pC("a+b-a=b (and variants)").pC("($0 + $1 + (- $0))").A("$1").A(258);
   static bso.Av UT = bso.Av.pC("((x&a)|b)-^((x&a)|c) => b-c [with conditions]")
      .pC("(($0 & #1) | #2) _ncsub (($0 & #1) | #3)")
      .A("#2 - #3")
      .pC(new cbq())
      .A(258);
   static bso.Av E = bso.Av.pC("((x&a)^b)+(x&b) => (x&a)|b [with conditions]").pC("(($0 & #1) ^ #2) + ($0 & #2)").A("($0 & #1) | #2").pC(new cbr()).A(2);
   static bso.Av sY = bso.Av.pC("((x&a)^b)|(x&b) => (x&a)|b").pC("(($0 & #1) ^ #2) | ($0 & #2)").A("($0 & #1) | #2").A(2);
   static bso.Av ys = bso.Av.pC("(x&a)|b + (x&a)^c => b+c [with conditions]").pC("(($0 & #1) | #2) _ncadd (($0 & #1) ^ #3)").A("#4").pC(new cbs()).A(258);
   static bso.Av ld = bso.Av.pC("(~x&a)|b + (x&a)|c => a+b+c [with conditions]")
      .pC("(((~ $0) & #1) | #2) _ncadd (($0 & #1) | #3)")
      .A("#4")
      .pC(new cbt())
      .A(258);
   static bso.Av gp = bso.Av.pC("(x+a)-(x|a) => (x&a)==0?0:a [with a a power of 2]")
      .pC("($0 + #1) - ($0 | #1)")
      .pC("($0 - ($0 | #1)) + #1")
      .A("(($0 & #1) == 0) ? 0 : #1")
      .pC(new cbu())
      .A(258);
   static bso.Av oT = bso.Av.pC("((x & a) ^ b) + ((((~x | c) & d) | (x & e)) ^ f) => b+(d^f) [with conditions]")
      .pC("(($0 & #1) ^ #2) + (((((~ $0) | #3) & #4) | ($0 & #5)) ^ #6)")
      .A("#2 + (#4 ^ #6)")
      .pC(new cbv())
      .A(258);
   static bso.Av fI = bso.Av.pC("(X | 1) & (X ^ ~1)) => X&1 [generalized to other imms, with conds.]")
      .pC("($0 | #1) & ($0 ^ #2)")
      .A("$0 & #1")
      .pC(new cbw())
      .A(2);

   public cbp() {
      this.addTag("slow");
      this.addTag("deobfuscator");
      bpl.wS(this);
   }

   @Override
   public int perform() {
      return super.perform();
   }

   @Override
   protected Collection pC() {
      return Arrays.asList(pC, A, kS, wS, UT, E, sY, ys, ld, gp, oT, fI);
   }
}
