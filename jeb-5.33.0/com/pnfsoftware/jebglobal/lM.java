package com.pnfsoftware.jebglobal;

public enum lM {
   pC(0, "No error has occurred."),
   A(10, "Passed thread is null, is not a valid thread or has exited."),
   kS(11, "Thread group invalid."),
   wS(12, "Invalid priority."),
   UT(13, "If the specified thread has not been suspended by an event."),
   E(14, "Thread already suspended."),
   sY(15, "Thread has not been started or is now dead."),
   ys(20, "If this reference type has been unloaded and garbage collected."),
   ld(21, "Invalid class."),
   gp(22, "Class has been loaded but not yet prepared."),
   oT(23, "Invalid method."),
   fI(24, "Invalid location."),
   WR(25, "Invalid field."),
   NS(30, "Invalid jframeID."),
   vP(31, "There are no more Java or JNI frames on the call stack."),
   xC(32, "Information about the frame is not available."),
   ED(33, "Operation can only be performed on current frame."),
   Sc(34, "The variable is not an appropriate type for the function used."),
   ah(35, "Invalid slot."),
   eP(40, "Item already set."),
   UO(41, "Desired element not found."),
   Ab(50, "Invalid monitor."),
   rl(51, "This thread doesn't own the monitor."),
   z(52, "The call has been interrupted before completion."),
   Ek(60, "The virtual machine attempted to read a class file and determined that the file is malformed or otherwise cannot be interpreted as a class file."),
   hK(61, "A circularity has been detected while initializing a class."),
   Er(62, "The verifier detected that a class file, though well formed, contained some sort of internal inconsistency or security problem."),
   FE(63, "Adding methods has not been implemented."),
   Aj(64, "Schema change has not been implemented."),
   EX(65, "The state of the thread has been modified, and is now inconsistent."),
   LM(
      66,
      "A direct superclass is different for the new class version, or the set of directly implemented interfaces is different and canUnrestrictedlyRedefineClasses is false."
   ),
   mv(67, "The new class version does not declare a method declared in the old class version and canUnrestrictedlyRedefineClasses is false."),
   sO(68, "A class file has a version number not supported by this VM."),
   os(69, "The class name defined in the new class file is different from the name in the old class object."),
   Cu(70, "The new class version has different modifiers and and canUnrestrictedlyRedefineClasses is false."),
   hZ(
      71,
      "A method in the new class version has different modifiers than its counterpart in the old class version and and canUnrestrictedlyRedefineClasses is false."
   ),
   UW(99, "The functionality is not implemented in this virtual machine."),
   PR(100, "Invalid pointer."),
   cX(101, "Desired information is not available."),
   DQ(102, "The specified event type id is not recognized."),
   ZN(103, "Illegal argument."),
   OB(110, "The function needed to allocate memory and no more memory was available for allocation."),
   pF(111, "Debugging has not been enabled in this virtual machine. JVMTI cannot be used."),
   Bc(112, "The virtual machine is not running."),
   OI(113, "An unexpected internal error has occurred."),
   Bf(115, "The thread being used to call this function is not attached to the virtual machine. Calls must be made from attached threads."),
   Pe(500, "object type id or class tag."),
   ck(502, "Previous invoke not complete."),
   RW(503, "Index is invalid."),
   e(504, "The length is invalid."),
   xM(506, "The string is invalid."),
   kU(507, "The class loader is invalid."),
   Kq(508, "The array is invalid."),
   go(509, "Unable to load the transport."),
   JF(510, "Unable to initialize the transport."),
   Nq(511, "The method is native."),
   pg(512, "The count is invalid.");

   private final int gj;
   private final String ZD;

   private lM(int var3, String var4) {
      this.gj = var3;
      this.ZD = var4;
   }

   public int pC() {
      return this.gj;
   }
}
