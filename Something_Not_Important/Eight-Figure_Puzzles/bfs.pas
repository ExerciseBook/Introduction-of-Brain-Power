{$MODE DELPHI}


Const
	StartStatus = longint(283164705);
	TargetStatus = longint(123804765);
	
Var
	visitHistory : record
						p : array {[0..362880]} of longint;
						len : longint;
					end;
	
function VisitHistory_GetStatus(a:longint):longint;
Var
	i : longint;
Begin
	result := -1;
	for i:=0 to visitHistory.len-1 do begin
		if visitHistory.p[i]=a then begin
			result:=i;
			exit;
		end;
	end;
End;

function VisitHistory_SetStatus(a:longint;status:longint):longint;
Var
	Position : longint;
Begin
	Position := VisitHistory_GetStatus(a);
	if Position = -1 then begin
		if status=0 then exit();
		Position := visitHistory.len;
		inc(visitHistory.len);
		setLength(visitHistory.p,visitHistory.len);
		visitHistory.p[Position] := a;
		exit(1);
	end
	else
	begin
		if status<>0 then exit(0);
		visitHistory.p[Position] := 0;
	end;
End;

	// 1 2 3
	// 4 5 6
	// 7 8 9

function Move_Up(nowstatus:longint;Var nextstatus:longint):boolean;
Var
	s:string;
	p,q:longint;
Begin
	str(nowstatus,s);
	p:=pos('0',s);if p=0 then begin p:=1; s:='0'+s; end;
	if p<=3 then exit(false);
	q:=p-3;
	
	s[p]:=s[q];
	s[q]:='0';
	
	val(s,nextstatus);
	
	exit(true);
End;

function Move_Down(nowstatus:longint;Var nextstatus:longint):boolean;
Var
	s:string;
	p,q:longint;
Begin
	str(nowstatus,s);
	p:=pos('0',s);if p=0 then begin p:=1; s:='0'+s; end;
	if p>=7 then exit(false);
	q:=p+3;
	
	s[p]:=s[q];
	s[q]:='0';
	
	val(s,nextstatus);
	
	exit(true);
End;

function Move_Left(nowstatus:longint;Var nextstatus:longint):boolean;
Var
	s:string;
	p,q:longint;
Begin
	str(nowstatus,s);
	p:=pos('0',s);if p=0 then begin p:=1; s:='0'+s; end;
	if p mod 3 = 1 then exit(false);
	q:=p-1;
	
	s[p]:=s[q];
	s[q]:='0';
	
	val(s,nextstatus);
	
	exit(true);
End;

function Move_Right(nowstatus:longint;Var nextstatus:longint):boolean;
Var
	s:string;
	p,q:longint;
Begin
	str(nowstatus,s);
	p:=pos('0',s);if p=0 then begin p:=1; s:='0'+s; end;
	if p mod 3 = 0 then exit(false);
	q:=p+1;
	
	s[p]:=s[q];
	s[q]:='0';
	
	val(s,nextstatus);
	
	exit(true);
End;

Var
	nowstatus : longint;
	nextstatus : longint;
	i : longint;
Begin
	nowstatus:=StartStatus;
	visitHistory.len:=0;
	VisitHistory_SetStatus(nowstatus,1);
	i:=0;
	while i<=visitHistory.len-1 do begin
		nowstatus:=visitHistory.p[i];
		if nowstatus=TargetStatus then exit;
		if Move_Up(nowstatus,nextstatus) and (VisitHistory_GetStatus(nextstatus)=-1) then begin			
			VisitHistory_SetStatus(nextstatus,1);
			//visitHistory.o[visitHistory.len-1]:=nowstatus;
			writeln(nowstatus,' -> ',nextstatus);
			if nextstatus=TargetStatus then exit();
		end;
		if Move_Right(nowstatus,nextstatus) and (VisitHistory_GetStatus(nextstatus)=-1) then begin
			VisitHistory_SetStatus(nextstatus,1);
			//visitHistory.o[visitHistory.len-1]:=nowstatus;
			writeln(nowstatus,' -> ',nextstatus);
			if nextstatus=TargetStatus then exit();
		end;
		if Move_Down(nowstatus,nextstatus) and (VisitHistory_GetStatus(nextstatus)=-1) then begin
			VisitHistory_SetStatus(nextstatus,1);
			//visitHistory.o[visitHistory.len-1]:=nowstatus;
			writeln(nowstatus,' -> ',nextstatus);
			if nextstatus=TargetStatus then exit();
		end;
		if Move_Left(nowstatus,nextstatus) and (VisitHistory_GetStatus(nextstatus)=-1) then begin
			VisitHistory_SetStatus(nextstatus,1);
			//visitHistory.o[visitHistory.len-1]:=nowstatus;
			writeln(nowstatus,' -> ',nextstatus);
			if nextstatus=TargetStatus then exit();
		end;
		inc(i);
	end;

End.