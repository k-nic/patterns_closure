#!/usr/local/bin/perl
#
# composite series of images over a background image
#



$n = $ARGV[0];
$k = $ARGV[1];

#$zeros = join(",", split("", "0" x $n));
#print "$zeros\n";
for ($i=0; $i < 2**($n+$k); $i++) {
	$num = $i;
	my $bin = sprintf ("%b", $num);
	my @bits = split(//, $bin);
	my $sum = 0;
	map { $sum += $_ } @bits;
	if(($sum != $n) || ($num%2 == 0))
	{
		next;
	}
	my $count = 1;
	for ($j=0; $j < scalar(@bits); $j++) {
		if($bits[$j] == 1)
		{
			$bits[$j] = $count;
			$count++;
		}
	}
	$line = join(",", @bits);
	$line =~ s/0/*/g;
	print $line."\n";
}

