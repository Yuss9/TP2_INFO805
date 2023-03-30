DATA SEGMENT
	 prixHt DD
	 prixTtc DD
DATA ENDS
CODE SEGMENT
mov ebp, 0
	mov eax, 200
	mov eax, prixHt
	mov let, eax
	mov eax, 119
	pop ebx
	mul ebx
	mov eax, 100
	pop ebx
	div ebx
	mov eax, prixTtc
	mov let, eax
CODE ENDS