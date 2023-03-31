DATA SEGMENT
	 a DD
	 b DD
	 aux DD
DATA ENDS
CODE SEGMENT
	in eax
	mov a, eax
	in eax
	mov b, eax
	mov eax,  a
	out eax
CODE ENDS