package com.pnfsoftware.jebglobal;

import java.util.Arrays;
import java.util.Collection;

public class cgl extends cay {
   static bxc.eo q = bxc.eo.q("Obfuscated integer additions")
      .RF("0 - ((0 - $0) - $1)")
      .RF("0 - (0 - ($0 + $1))")
      .RF("($0 & $1) + ($0 | $1)")
      .RF("(($0 & $1) * 2) + ($0 ^ $1)")
      .RF("(($0 | $1) * 2) - ($0 ^ $1)")
      .RF("($0 - (~ $1)) - 1")
      .xK("$0 + $1")
      .RF(2);
   static bxc.eo RF = bxc.eo.q("Obfuscated integer subtractions")
      .RF("0 - ((0 - $0) + $1)")
      .RF("0 - (0 - ($0 - $1))")
      .RF("($0 & (~ $1)) - ((~ $0) & $1)")
      .RF("($0 ^ $1) - (2 * ((~ $0) & $1))")
      .RF("(2 * ($0 & (~ $1))) - ($0 ^ $1)")
      .RF("$0 + (~ $1) + 1")
      .xK("$0 - $1")
      .RF(2);
   static bxc.eo xK = bxc.eo.q("Obfuscated bitwise-xor [with conditions]")
      .RF("($0 + $1 + (- (($0 & $1) * 2)))")
      .RF("((2 * ($0 | $1)) + (- $0) + (- $1))")
      .RF("($0 + (- $1) + (2 * ((~ $0) & $1)))")
      .xK("$0 ^ $1")
      .RF(2);
   static bxc.eo Dw = bxc.eo.q("a+b-a=b (and variants)").RF("($0 + $1 + (- $0))").xK("$1").RF(258);
   static bxc.eo Uv = bxc.eo.q("((x&a)|b)-^((x&a)|c) => b-c [with conditions]")
      .RF("(($0 & #1) | #2) _ncsub (($0 & #1) | #3)")
      .xK("#2 - #3")
      .q(new cgm())
      .RF(258);
   static bxc.eo oW = bxc.eo.q("((x&a)^b)+(x&b) => (x&a)|b [with conditions]").RF("(($0 & #1) ^ #2) + ($0 & #2)").xK("($0 & #1) | #2").q(new cgn()).RF(2);
   static bxc.eo gO = bxc.eo.q("((x&a)^b)|(x&b) => (x&a)|b").RF("(($0 & #1) ^ #2) | ($0 & #2)").xK("($0 & #1) | #2").RF(2);
   static bxc.eo nf = bxc.eo.q("(x&a)|b + (x&a)^c => b+c [with conditions]").RF("(($0 & #1) | #2) _ncadd (($0 & #1) ^ #3)").xK("#4").q(new cgo()).RF(258);
   static bxc.eo gP = bxc.eo.q("(~x&a)|b + (x&a)|c => a+b+c [with conditions]")
      .RF("(((~ $0) & #1) | #2) _ncadd (($0 & #1) | #3)")
      .xK("#4")
      .q(new cgp())
      .RF(258);
   static bxc.eo za = bxc.eo.q("(x+a)-(x|a) => (x&a)==0?0:a [with a a power of 2]")
      .RF("($0 + #1) - ($0 | #1)")
      .RF("($0 - ($0 | #1)) + #1")
      .xK("(($0 & #1) == 0) ? 0 : #1")
      .q(new cgq())
      .RF(258);
   static bxc.eo lm = bxc.eo.q("((x & a) ^ b) + ((((~x | c) & d) | (x & e)) ^ f) => b+(d^f) [with conditions]")
      .RF("(($0 & #1) ^ #2) + (((((~ $0) | #3) & #4) | ($0 & #5)) ^ #6)")
      .xK("#2 + (#4 ^ #6)")
      .q(new cgr())
      .RF(258);
   static bxc.eo zz = bxc.eo.q("(X | 1) & (X ^ ~1)) => X&1 [generalized to other imms, with conds.]")
      .RF("($0 | #1) & ($0 ^ #2)")
      .xK("$0 & #1")
      .q(new cgs())
      .RF(2);

   public cgl() {
      this.addTag("slow");
      this.addTag("deobfuscator");
      bto.Uv(this);
   }

   @Override
   public int perform() {
      return super.perform();
   }

   @Override
   protected Collection q() {
      return Arrays.asList(q, RF, xK, Dw, Uv, oW, gO, nf, gP, za, lm, zz);
   }
}
